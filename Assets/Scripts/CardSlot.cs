using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class CardSlot : MonoBehaviour, IDropHandler, IPointerClickHandler {
    public Image image;
    public Color selectedColor, unselectedColor;

    public void Awake() {
       // Deselect();
    }
    public void Select() {
       image.color = selectedColor;
    }
    public void Deselect() {
        image.color=unselectedColor;
    }

    // function called when card is dropped on current slot
    public void OnDrop(PointerEventData eventData) {
        // if current slot is empty
        if(transform.childCount == 0) {
            // makes card a child of this slot
            Card invenItem = eventData.pointerDrag.GetComponent<Card>();
            invenItem.SetParentAfterDrag(transform);
        }
    }
    // determines when slot is clicked
    public void OnPointerClick(PointerEventData pointerData) {
        Debug.Log("click");
        
    }
}
