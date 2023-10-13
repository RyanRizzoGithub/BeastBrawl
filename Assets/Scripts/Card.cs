using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using Inventory.Model;
using TMPro;

public class Card : MonoBehaviour,IBeginDragHandler, IEndDragHandler,IDragHandler
{
    [SerializeField] public Image image;
    [SerializeField] public Transform parentAfterDrag;
    [SerializeField] public CardSO cardData;
    [SerializeField] private int health =1;
    [SerializeField] private TMP_Text healthText;
    public void Start() {
       InitialiseItem(cardData);

        
    }
    //intialize card image 
    public void InitialiseItem(CardSO cardData) {
        image.sprite = cardData.GetImage();
        RefreshHealth();
    }
    public void RefreshHealth() {
        healthText.text = health.ToString();
    }

    // fucntion runs when card starts being dragged.
    public void OnBeginDrag(PointerEventData eventData) {
         
        image.raycastTarget = false;
        // saves where the card is dragged from
        parentAfterDrag = transform.parent;
        transform.SetParent(transform.root);
        
    }
    // fucntion runs when card being dragged.
    public void OnDrag(PointerEventData eventData) {
        transform.position = Input.mousePosition;
    }
    // fucntion runs when card is let go.
    public void OnEndDrag(PointerEventData eventData) {
        image.raycastTarget = true;
        // returns card to old slot if its not dropped on a free slot
        transform.SetParent(parentAfterDrag);
    }
    public Transform GetParentAfterDrag() {
        return parentAfterDrag;
    }
    public void SetParentAfterDrag(Transform newParent) {
         parentAfterDrag = newParent;
    }


}
