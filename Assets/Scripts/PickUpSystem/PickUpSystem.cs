using Inventory.Model;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PickUpSystem : MonoBehaviour
{
    [SerializeField]
    private InventorySO inventoryData;

    private void OnTriggerEnter2D(Collider2D collision) {
        ItemPickUp item = collision.GetComponent<ItemPickUp>();
        if (item != null) {
            // adds item and gets the amount of left over item(s) if 
            // couldnt be added
            Debug.Log("adding");
            int reminder = inventoryData.AddItem(item.GetInventoryItem(), item.GetCount());
            // if none are left it destroys object
            if (reminder == 0)
                item.DestroyItem();
            // if any left it updates the count
            else
                item.SetCount(reminder);
        }
    }
}
