using Inventory.UI;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MouseFollower : MonoBehaviour
{
    [SerializeField] private Canvas canvas;
    [SerializeField] private InventoryItemUI item;

    public void Awake() {
        
        canvas = transform.root.GetComponent<Canvas>();
        item = GetComponentInChildren<InventoryItemUI>();
    }
    public void SetData (Sprite sprite, int count) {
        item.SetData (sprite, count);
    }
    public void Clear() {
        item.SetData(null, 0);
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }
    /*---------------------------------------------------------------------
 |  Method Show()
 |
 |  Purpose: Follows object with mouse 
 |  
 |   Parameters: Sprite sprite = the sprite to fill current slot
 |               string itemName = name of item in current slot
 |               string itemDescription = description of item in current slot
 |
 |  Returns: none
 *-------------------------------------------------------------------*/
    public void Follow() {
        // getting postion of mouse
        Vector2 position;
        RectTransformUtility.ScreenPointToLocalPointInRectangle((RectTransform)canvas.transform, 
            Input.mousePosition, canvas.worldCamera, out position);
        transform.position = canvas.transform.TransformPoint(position);
    }

    public void Toggle(bool val) {
        Debug.Log($"Item Toggle {val}");
        gameObject.SetActive(val);
    }
    // Update is called once per frame
    void Update()
    {
        Follow();

    }
}
