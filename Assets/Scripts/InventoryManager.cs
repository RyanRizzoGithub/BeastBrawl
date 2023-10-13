using Inventory.Model;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InventoryManager : MonoBehaviour
{
    public InvenotrySlot[] invenotrySlots;
    public GameObject invenItemPrefab;
    // Start is called before the first frame update
    public bool AddItem(ItemSO item) {
        for(int i=0;i<invenotrySlots.Length;i++) {
            InvenotrySlot slot = invenotrySlots[i];
            InvenItem curItem = slot.GetComponentInChildren<InvenItem>();
            if(curItem != null) {
                SpawnNewItem(item, slot);
                return true;
            }
        }
        return false;

    }
    public void SpawnNewItem(ItemSO item,InvenotrySlot slot) {
        GameObject newItem = Instantiate(invenItemPrefab, slot.transform);
        InvenItem invenItem = newItem.GetComponentInChildren<InvenItem>();
        invenItem.InitialiseItem(item);

    }
}
