using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

namespace Inventory.UI {
    public class InventoryPageUI : MonoBehaviour {
        [SerializeField] private InventoryItemUI item;
        [SerializeField] private RectTransform controlPanel;
        [SerializeField] private InventoryItemDescriptionUI itemDescription;
        [SerializeField] private MouseFollower mouseFollower;
        public List<InventoryItemUI> itemList = new List<InventoryItemUI>();
        public event Action<int> onDescriptionRequest, onItemActionRequest, onStartDrag;
        public event Action<int, int> onSwapItems;
        private int currentItemIndex = -1;
        public void Awake() {
            // Hide();
            mouseFollower.Toggle(false);
        }
        /*---------------------------------------------------------------------
        |  Method Show()
        |
        |  Purpose: Creates itemList by filling the list of inventoryUI
        |           with "inventorySize" elements
        |  
        |   Parameters: int inventorySize = size of listoftiems 
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        public void CreateInventoryUI(int inventorySize) {
            // initlaizes invenotry based on how many cildren the controlPanel gameboject has.
            // this is determined in the unity editor
            int childCount = controlPanel.childCount;
            for (int i = 0; i < childCount; i++) {

                InventoryItemUI curItem = controlPanel.GetChild(i).GetComponent<InventoryItemUI>();
                curItem.transform.SetParent(controlPanel);
                itemList.Add(curItem);
                // handles what happens if itemUI is left clicked
                curItem.onItemClicked += HandleItemSelection;
                // handles what happens if itemUI being dragged
                curItem.onItemBeginDrag += HandleBeginDrag;
                // handesl what happens if itemUI is being dropped on another itemUI
                curItem.onItemDroppedOn += HandleSwap;
                // handles what happens when item is dropped after being dragged.
                curItem.onItemEndDrag += HandleEndDrg;
                // handles what happens if  itemUI right clicked
                curItem.onRightMouseBtnClick += HandleShowItemActions;
            }
        }
        /*---------------------------------------------------------------------
        |  Method  HandleItemSelection(InventoryItemUI currentItem)
        |
        |  Purpose: Sends the inforamtion about the slot 
        |           being selected to inventory controller
        |
        |   Parameters: InventoryItemUI currentItem = the current slot being selected
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleItemSelection(InventoryItemUI currentItem) {
            int index = itemList.IndexOf(currentItem);
            if (index == -1) {
                return;
            }
           // onDescriptionRequest?.Invoke(index);
            if (onDescriptionRequest != null) {
                onDescriptionRequest.Invoke(index);
            }
        }
        /*---------------------------------------------------------------------
        |  Method HandleBeginDrag(InventoryItemUI currentItem)
        |
        |  Purpose: Sends the inforamtion about the slot 
        |           being dragged to inventory controller
        |
        |   Parameters: InventoryItemUI currentItem = the current slot being dragged
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleBeginDrag(InventoryItemUI currentItem) {

            int index = itemList.IndexOf(currentItem);
            if (index == -1) {
                //mouseFollower.Toggle(false);
                return;
            }
            // grabs index of clicked on slot
            currentItemIndex = index;
            // selects current slot
            HandleItemSelection(currentItem);
            if (onStartDrag != null) {
                onStartDrag.Invoke(index);
            }
            /*
            mouseFollower.Toggle(true);
            mouseFollower.SetData(index==0?image:image2, count);*/
        }

        public void CreateDraggedItem(Sprite sprite, int count) {
            mouseFollower.Toggle(true);
            mouseFollower.SetData(sprite, count);
        }

        /*---------------------------------------------------------------------
        |  Method HandleSwap(InventoryItemUI currentItem)
        |
        |  Purpose: Sends the inforamtion about the slot 
        |           being swapped to inventory controller
        |
        |   Parameters: InventoryItemUI currentItem = the current slot being swapped
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleSwap(InventoryItemUI currentItem) {
            int index = itemList.IndexOf(currentItem);
            
            // tried to swap with invalid or out of bounds
            if (index <= -1) {
                return;
            }
            // swaps items
            if (onSwapItems != null) {
                onSwapItems.Invoke(currentItemIndex, index);
            }
            // selects currenly dragged item
            HandleItemSelection(currentItem);
        }

        /*---------------------------------------------------------------------
        |  Method HandleSwap(InventoryItemUI currentItem)
        |
        |  Purpose: Clears the draggedItem when mouse is let go
        |
        |   Parameters: InventoryItemUI currentItem = the current slot being let go
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleEndDrg(InventoryItemUI currentItem) {
            ResetDraggedItem();
            //mouseFollower.Clear();
        }

        private void ResetDraggedItem() {
            mouseFollower.Toggle(false);
            currentItemIndex = -1;
        }

        public void UpdateData(int itemIndex, Sprite itemImage, int itemCount) {
            // checks if inbounds
            if (itemList.Count > itemIndex) {
                // update data
                itemList[itemIndex].SetData(itemImage, itemCount);
            }

        }
        private void HandleShowItemActions(InventoryItemUI currentItem) {
            int index = itemList.IndexOf(currentItem);

            //check if slot tring to preform is in bounds
            if (index <= -1) {
                return;
            }
            onItemActionRequest?.Invoke(index);

        }
        /*---------------------------------------------------------------------
        |  Method Show()
        |
        |  Purpose: Shows the game object from view
        |
        |   Parameters: none
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        public void Show() {
            gameObject.SetActive(true);
            // ensure the descriptiuon page is cleared when player opens inventory
            itemDescription.ResetDescription();
            ResetSelected();
        }

        /*---------------------------------------------------------------------
        |  Method Show()
        |
        |  Purpose: hides the game object from view
        |  
        |   Parameters: none
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        public void Hide() {
            gameObject.SetActive(false);
            ResetDraggedItem();
        }

        private void ResetSelected() {
            itemDescription.ResetDescription();
            DeselectAllItems();
        }
        private void DeselectAllItems() {
            // deselects all items 
            foreach (InventoryItemUI item in itemList) {
                item.Deselect();
            }
        }
        public List<InventoryItemUI> GetItemList() {
            return itemList;
        }

        public void UpdateDescription(int curItemIndex, Sprite image, string name, string description) {
            //updates desctiption to currently slected item
            itemDescription.SetDescription(image, name, description);
            //clears all current selections
            DeselectAllItems();
            // selects the desired slot
            itemList[curItemIndex].Select();
        }

        public void ResetAllItems() {
            foreach(var item in itemList) {
                item.ResetData();
                item.Deselect();
            }
        }
    }
}
