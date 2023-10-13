using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using Inventory.Model;
using TMPro;

public class InvenItem : MonoBehaviour,IBeginDragHandler, IEndDragHandler,IDragHandler
{
    public Image image;
    public Transform parentAfterDrag;
    public ItemSO item;
    [field: SerializeField] public int count =1;
   [field:SerializeField] public TMP_Text countText;
    public void Start() {
       InitialiseItem(item);

        
    }
    public void InitialiseItem(ItemSO item) {
        image.sprite = item.GetImage();
        RefreshCount();
    }
    public void RefreshCount() {
        countText.text = count.ToString();
    }
    
    public void OnBeginDrag(PointerEventData eventData) {
        image.raycastTarget = false;
        parentAfterDrag = transform.parent;
        transform.SetParent(transform.root);
        
    }

    public void OnDrag(PointerEventData eventData) {
        transform.position = Input.mousePosition;
    }

    public void OnEndDrag(PointerEventData eventData) {
        image.raycastTarget = true;
        transform.SetParent(parentAfterDrag);
    }

 
}
