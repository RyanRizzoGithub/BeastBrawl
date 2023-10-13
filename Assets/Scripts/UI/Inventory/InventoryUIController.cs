using Inventory.Model;
using Inventory.UI;
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Inventory {
    public class InventoryController : MonoBehaviour {
        [SerializeField] private InventoryPageUI inventoryUI;
        [SerializeField] private bool isOpen;
        [SerializeField] private int inventorySize;
        [SerializeField] private InventorySO inventoryData;
        [SerializeField] private PlayerController playerController;
        public List<InventoryItem> initialItems = new List<InventoryItem>();

        public void Start() {
            // creates inventory  
            PrepareUI();
            PrepareInventory();
            playerController = GetComponent<PlayerController>();
        }
        public void Update() {
            // opens and closes inventory page
            //Debug.Log("--- " + playerController.GetIsInentoryOpen());
            if (Input.GetKeyUp(KeyCode.I)) {
                Debug.Log(inventoryUI.itemList.Count);
                if (inventoryUI.isActiveAndEnabled == false) {
                    inventoryUI.Show();
                    //updates UI to match data
                    foreach (var item in inventoryData.GetCurrentInventoryState()) {
                        inventoryUI.UpdateData(item.Key,
                            item.Value.item.GetImage(),
                            item.Value.count);
                    }
                    isOpen = true;
                }
                else {
                    inventoryUI.Hide();
                    isOpen = false;
                }
            }
        }
        public void PrepareInventory() {
            inventoryData.Initialize();
            // updates invenotry UI 
            
            foreach(InventoryItem item in initialItems) {
                if (item.IsEmpty()) {
                    continue;
                }
                inventoryData.AddItem(item);
            }
            inventoryData.onInventoryUpdated += UpdateInventoryUI;
        }

        /*---------------------------------------------------------------------
        |  Method UpdateInventoryUI(Dictionary<int, InventoryItem> inventorySate)
        |
        |  Purpose: Updates the UI when data is changed in the model
        |
        |   Parameters: Dictionary<int, InventoryItem> inventorySate dict that 
        |               represents the current data in the inventory model
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void UpdateInventoryUI(Dictionary<int, InventoryItem> inventorySate) {
            // resets previous information
            inventoryUI.ResetAllItems();
            // updates UI with current inventory data
            foreach(var item in inventorySate) {
                inventoryUI.UpdateData(item.Key, 
                    item.Value.item.GetImage(),
                    item.Value.count);
            }
        }

        /*---------------------------------------------------------------------
        |  Method PrepareUI()
        |
        |  Purpose: Sets up UI toaccept draggs,swaps, and selecting items
        |
        |   Parameters: none
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        public void PrepareUI() {
            inventoryUI.CreateInventoryUI(inventorySize);
            inventoryUI.onDescriptionRequest += HandleDescriptionRequest;
            inventoryUI.onSwapItems += HandleSwapItems;
            inventoryUI.onStartDrag += HandleDragging;
            inventoryUI.onItemActionRequest += HandleItemActionRequest;
        }

        private void HandleItemActionRequest(int curItemIndex) {
            InventoryItem inventoryItem = inventoryData.GetItemAt(curItemIndex);
            // avoids empty slots
            if (inventoryItem.IsEmpty()) {
                return;
            }
            IItemAction itemAction = inventoryItem.item as IItemAction;
            if(itemAction != null) {
                itemAction.PerformAction(gameObject,null);
            }
            IDestroyableItem destroyableItem = inventoryItem.item as IDestroyableItem;
            if (destroyableItem != null) {
                Debug.Log("remvoing action");
                inventoryData.RemoveItem(curItemIndex, 1);
            }
        }

        /*---------------------------------------------------------------------
        |  Method HandleDragging(int curItemIndex)
        |
        |  Purpose: Handles dragging item when clicking on inventory item
        |
        |   Parameters: int curItemIndex = index of item being dragged
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleDragging(int curItemIndex) {
            InventoryItem inventoryItem = inventoryData.GetItemAt(curItemIndex);
            // avoids empty slots
            if (inventoryItem.IsEmpty()) {
                return;
            }
            // creates dragged item 
            inventoryUI.CreateDraggedItem(inventoryItem.item.GetImage(), inventoryItem.count);
        }

        /*---------------------------------------------------------------------
        |  Method HandleSwapItems(int curitemIndex, int itemIndexSwap)
        |
        |  Purpose: Handles swaping items between two slots
        |
        |   Parameters: int curitemIndex = index of item being dragged
        |               int itemIndexSwap = index of item being hovered over
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleSwapItems(int curitemIndex, int itemIndexSwap) {
            inventoryData.SwapItems(curitemIndex, itemIndexSwap);
        }

        /*---------------------------------------------------------------------
        |  Method HandleSwapItems(int curitemIndex, int itemIndexSwap)
        |
        |  Purpose: Handles when showing description when clicking on item
        |
        |   Parameters: int curitemIndex = index of item being selected
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        private void HandleDescriptionRequest(int curItemIndex) {

            InventoryItem curInventoryItem = inventoryData.GetItemAt(curItemIndex);
            // avoids empty slots
            if (curInventoryItem.IsEmpty()) {
                return;
            }
            // updates description page
            ItemSO curItem = curInventoryItem.item;
            inventoryUI.UpdateDescription(curItemIndex, curItem.GetImage(),
                curItem.GetName(), curItem.description);

        }

        public bool IsOpen() {
            return isOpen;
        }
    }
}

