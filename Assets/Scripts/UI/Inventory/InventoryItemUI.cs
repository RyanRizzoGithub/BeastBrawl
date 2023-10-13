using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;
namespace Inventory.UI {
    public class InventoryItemUI : MonoBehaviour, IPointerClickHandler, IBeginDragHandler, IEndDragHandler, IDropHandler, IDragHandler {
        [SerializeField] private Image itemImage;
        [SerializeField] private TMP_Text countText;
        [SerializeField] private Image borderImage;
        // delagate that allows to call the following functions 
        public event Action<InventoryItemUI> onItemClicked, onItemDroppedOn,
            onItemBeginDrag, onItemEndDrag, onRightMouseBtnClick;
        public bool empty = true;


        public void Awake() {
            ResetData();
            Deselect();
        }

        public void Deselect() {
            borderImage.enabled = false;
        }

        public void ResetData() {
            itemImage.gameObject.SetActive(false);
            empty = true;

        }

        public void SetData(Sprite sprite, int qunatity) {
            if (sprite == null || qunatity < 0) {
                empty = false;
                return;
            }
            itemImage.gameObject.SetActive(true);
            itemImage.sprite = sprite;
            countText.text = qunatity + "";
            empty = false;
        }

        public void Select() {
            itemImage.enabled = true;
            borderImage.enabled = true;

        }

        public void OnPointerClick(PointerEventData pointerData) {

            if (pointerData.button == PointerEventData.InputButton.Right) {
                //  if OnRightMouseBtnClick is not bull then run code
                //  onRightMouseBtnClick?.Invoke(this);
                if (onRightMouseBtnClick != null) {
                    onRightMouseBtnClick.Invoke(this);
                }

            }
            // left click
            else {
                if (onItemClicked != null) {
                    onItemClicked.Invoke(this);
                }
                //onItemClicked?.Invoke(this);
            }
        }

        public void OnBeginDrag(PointerEventData eventData) {
            if (empty) {
                return;
            }
            if (onItemBeginDrag != null) {
                onItemBeginDrag.Invoke(this);
            }
        }

        public void OnEndDrag(PointerEventData eventData) {

            if (onItemEndDrag != null) {
                onItemEndDrag.Invoke(this);
            }
        }

        public void OnDrop(PointerEventData eventData) {
            if (empty) {
                //InvenItem invenItem = eventData.pointerDrag.GetComponent<InvenItem>();
                //invenItem.parentAfterDrag = transform;
                Debug.Log(eventData.pointerDrag.GetComponent<InventoryItemUI>());
            }
                if (onItemDroppedOn != null) {
                onItemDroppedOn.Invoke(this);
            }
        }

        public void OnDrag(PointerEventData eventData) {

        }
    }
}
