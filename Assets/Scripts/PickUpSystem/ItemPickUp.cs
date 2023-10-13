using Inventory.Model;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ItemPickUp : MonoBehaviour
{
    [field: SerializeField]
    private ItemSO inventoryItem { get; set; }

    [field: SerializeField]
    private int count { get; set; } = 1;

    [SerializeField]
    private AudioSource audioSource;

    [SerializeField]
    private float duration = 0.3f;
    // Start is called before the first frame update
    private void Start() {
       
        GetComponent<SpriteRenderer>().sprite = inventoryItem.GetImage();
    }

    public void DestroyItem() {
        // allows player to walk through item
        GetComponent<Collider2D>().enabled = false; 
        StartCoroutine(AnimateItemPickup());

    }

    /*---------------------------------------------------------------------
     *  Method AddItem(ItemSO item, int count)
     *
     *  Purpose: Animaters item being picked up by shrinking it 
     *           to nothing at speed of 'duration' then destroys it
     *
     *   Parameters: none
     *
     *  Returns: none
     *-------------------------------------------------------------------*/
    private IEnumerator AnimateItemPickup() {
        audioSource.Play();
        // grabs current item size
        Vector3 startScale = transform.localScale;
        // is zero so item will shrink to nothing when picked up
        Vector3 endScale = Vector3.zero;
        float currentTime = 0;
        // shrinks add speed of 'duration'
        while (currentTime < duration) {
            currentTime += Time.deltaTime;
            transform.localScale =
                Vector3.Lerp(startScale, endScale, currentTime / duration);
            // waits until next frame to return
            yield return null;
        }
        // destroys item
        Destroy(gameObject);
    }
    
    public ItemSO GetInventoryItem() {
        return inventoryItem;
    }
    public int GetCount() {
        return count;
    }
    public void SetCount(int newCount) {
        count=newCount;
    }

}
