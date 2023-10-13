using System;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

namespace Inventory.Model {
    [CreateAssetMenu]
    public class InventorySO : ScriptableObject {

        [SerializeField] private List<InventoryItem> inventoryItems;
        [field: SerializeField] private int size { get; set; } = 40;

        public event Action<Dictionary<int, InventoryItem>> onInventoryUpdated;

        public void Initialize() {
            inventoryItems = new List<InventoryItem>();
            for (int i = 0; i < size; i++) {
                inventoryItems.Add(InventoryItem.GetEmptyItem());
            }
        }

        /*---------------------------------------------------------------------
        *  Method AddItem(ItemSO item, int count)
        *
        *  Purpose: Adds items to inventory and returns the amount added
        *
        *   Parameters: ItemSO item = item(s) to add
        *               int count = amount of item(s) to add
        *
        *  Returns: int count = amount of items left over after adding
        *-------------------------------------------------------------------*/
        public int AddItem(ItemSO item, int count,List<ItemParameter> itemState= null) {
            // if adding non stackable item
            if (item.isStackable==false) {
                for (int i = 0; i < inventoryItems.Count; i++) {
                    // looks for first free slot and adds items until
                    // count == 0 
                    // also if multiple unstabckable items are added,
                    // then "AddToFirstFreeSlot" will add items in free slots until
                    // none are left
                    while (count>0 && IsInventoryFull() == false) {
                        count-= AddToFirstFreeSlot(item, 1);
                        //count--;
                    }
                    // updates UI to match data
                    InformAboutChange();
                    return count;            
                }
            }
            // adding item that is stackable
            count = AddStackableItem(item, count);
            // updates UI to match data
            InformAboutChange();
            return count;
        }

        /*---------------------------------------------------------------------
        *  Method AddToFirstFreeSlot(ItemSO item, int count)
        *
        *  Purpose: Looks through inventory for the frist free slot
        *           Then adds item to slot and returns 
        *           the amount of items added
        *
        *   Parameters: ItemSO item = item(s) to add
        *               int count = amount of item(s) to add
        *
        *  Returns: int count or 0 = amount of items added
        *-------------------------------------------------------------------*/
        private int AddToFirstFreeSlot(ItemSO item, int count, List<ItemParameter> itemState = null) {
            List<ItemParameter> curItemState;
            if (itemState == null) {
                curItemState = item.GetList();
            }
            else {
                curItemState = itemState;
            }
            InventoryItem newItem = new InventoryItem(item, count,itemState);
            // looks for first free empty slot
            for(int i = 0; i < inventoryItems.Count; i++) {
                // adds new item with count of 'count' in free slot
                if (inventoryItems[i].IsEmpty()) {
                    inventoryItems[i] = newItem;
                    // retruns amount of added items
                    return count;
                }
            }
            return 0;
        }

        /*---------------------------------------------------------------------
        *  Method IsInventoryFull()
        *
        *  Purpose: Looks through inventory to see if it's full
        *
        *   Parameters: 
        *
        *  Returns: true or false = if invenotry is full
        *-------------------------------------------------------------------*/
        private bool IsInventoryFull() {
            foreach (InventoryItem item in inventoryItems) {
                // empty slot is found
                if (item.IsEmpty()) {
                    return false;
                }
            }
            // every entry is full
            return true;
        }

        /*---------------------------------------------------------------------
        *  Method AddStackableItem(ItemSO item, int count)
        *
        *  Purpose: Tries to add stackable item(s) to invenotry.
        *           Does this by frist looking thorugh ther inventory and adding to 
        *           any mactbes found until count == 0 or there is nothing left to add.
        *           Then if there are left overs it looks through the invneotry again 
        *           looking for empty slots. Then adds items to slots until 
        *           the inventory if full or count == 0
        *
        *   Parameters: ItemSO item = item(s) to add
        *               int count = number of item(s) to add
        *
        *  Returns: int count or 0 = amounf of left over item(s) to add
        *-------------------------------------------------------------------*/
        private int AddStackableItem(ItemSO item, int count) {
            // loops through inventory slots
            for(int i = 0; i < inventoryItems.Count; i++) {
                // skips empty slots
               // InventoryItem curItem = inventoryItems[i];
                if (inventoryItems[i].IsEmpty()) {
                    continue;
                }
                // if matching item is found to add to stack
                if(inventoryItems[i].item.GetID() == item.GetID()) {
                    int amountLeftToAdd = inventoryItems[i].item.GetMaxStackSize() - inventoryItems[i].count;
                    // if amount to add is > than maxStackSize
                    if(count> amountLeftToAdd) {
                        // fills the currently found item and moves on 
                        // to add leftovers
                        inventoryItems[i] = inventoryItems[i].ChangeCount(inventoryItems[i].item.GetMaxStackSize());
                        count -= amountLeftToAdd;
                    }
                    // if curItem's count + the new item is < maxStackSize
                    else {
                        // adds item and updates the count 
                        inventoryItems[i] = inventoryItems[i].ChangeCount(inventoryItems[i].count + count);
                        InformAboutChange();
                        return 0;
                    }
                }
            }
            // looks to add left over items or if match can't be found
            while(count > 0 && IsInventoryFull() == false) {
                // splits the items up if count>MaxStackSize
                // chekcs if count is within range of maxStakcSize
                // if > maxStackSize then max is returned
                // if < 0 then 0 is given
                int newCount = Mathf.Clamp(count,0, item.GetMaxStackSize());
                count -= newCount;
                // adds left overs to first free slot
                AddToFirstFreeSlot(item, newCount);
            }
            // return count of any leftover items
            return count;
        }

        /*---------------------------------------------------------------------
        *  Method IsInventoryFull()
        *
        *  Purpose: creates dictionary to keep track of current items and 
        *           there index in the invenotry model
        *
        *   Parameters: 
        *
        *  Returns: retrunVal = dictioary to represent data
        *-------------------------------------------------------------------*/
        public Dictionary<int, InventoryItem> GetCurrentInventoryState() {
            Dictionary<int, InventoryItem> retrunVal = new Dictionary<int, InventoryItem>();
            for (int i = 0; i < inventoryItems.Count; i++) {
                if (inventoryItems[i].IsEmpty()) {
                    continue;
                }
                else {
                    retrunVal[i] = inventoryItems[i];
                }
            }
            return retrunVal;
        }

        public InventoryItem GetItemAt(int curItemIndex) {
            return inventoryItems[curItemIndex];
        }

        public void AddItem(InventoryItem item) {
            AddItem(item.item, item.count);
        }

        public void SwapItems(int curItemIndex, int itemIndexSwap) {
            InventoryItem curItemTemp = inventoryItems[curItemIndex];
            inventoryItems[curItemIndex] = inventoryItems[itemIndexSwap];
            inventoryItems[itemIndexSwap] = curItemTemp;
            InformAboutChange();

        }

        private void InformAboutChange() {

            onInventoryUpdated?.Invoke(GetCurrentInventoryState());
        }

        public void RemoveItem(int curItemIndex, int amount) {
            if(inventoryItems.Count>curItemIndex) {
                // avoid empty slots
                if (inventoryItems[curItemIndex].IsEmpty()) {
                    return;
                }
                int remainder = inventoryItems[curItemIndex].count - amount;
                // clears slot if empty has one left
                if (remainder <= 0) {
                    inventoryItems[curItemIndex] = InventoryItem.GetEmptyItem();
                }
                // updates count of item is used
                else {
                    Debug.Log("removing");
                    inventoryItems[curItemIndex] = 
                        inventoryItems[curItemIndex].ChangeCount(remainder);


                }
                InformAboutChange();
            }
        }
    }
    // a struct to keep track of inventory itme data
    // uses a struct to make data more secure
    // and can only be asscessed by invenotrySO
    [Serializable]
    public struct InventoryItem {
        public int count;
        public ItemSO item;
        //public bool isEmpty => item == null;
        public List<ItemParameter> itemState;

        public InventoryItem(ItemSO item, int count) {
            this.item = item;
            this.count = count;
            this.itemState = new List<ItemParameter>();
        }
        public InventoryItem(ItemSO item, int count,List<ItemParameter> itemState = null) {
            this.item = item;
            this.count = count;
            this.itemState = itemState;
        }
        public InventoryItem ChangeCount(int newCount) {
           // Debug.Log(itemState + "  "+newCount);

            return new InventoryItem(item, newCount);

        }

        public static InventoryItem GetEmptyItem() {
            return new InventoryItem(null, 0);
        }

        public bool IsEmpty() {
            if (item == null) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}

