using Inventory.Model;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InventoryManager : MonoBehaviour
{
    public CardSlot[] invenotrySlots;
    public GameObject invenItemPrefab;
    // Start is called before the first frame update
    public bool AddItem(CardSO cardData) {
        for(int i=0;i<invenotrySlots.Length;i++) {
            CardSlot slot = invenotrySlots[i];
            Card curCard = slot.GetComponentInChildren<Card>();
            if(curCard != null) {
                SpawnNewItem(cardData, slot);
                return true;
            }
        }
        return false;

    }
    public void SpawnNewItem(CardSO cardData, CardSlot slot) {
        GameObject newCardObj = Instantiate(invenItemPrefab, slot.transform);
        Card newCard = newCardObj.GetComponentInChildren<Card>();
        newCard.InitialiseItem(cardData);

    }
}
