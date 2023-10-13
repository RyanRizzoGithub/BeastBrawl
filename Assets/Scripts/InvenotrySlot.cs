using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class InvenotrySlot : MonoBehaviour, IDropHandler, IPointerClickHandler {
    public Image image;
    public Color selectedColor, unselectedColor;

    public void Awake() {
        Deselect();
    }
    public void Select() {
       image.color = selectedColor;
    }
    public void Deselect() {
        image.color=unselectedColor;
    }


    public void OnDrop(PointerEventData eventData) {
        if(transform.childCount == 0) {
            InvenItem invenItem = eventData.pointerDrag.GetComponent<InvenItem>();
            invenItem.parentAfterDrag = transform;
        }
    }
    // determines when slot is clicked
    public void OnPointerClick(PointerEventData pointerData) {
        Debug.Log("click");
        
    }
}
