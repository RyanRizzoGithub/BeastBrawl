using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

namespace Inventory.UI {
    public class InventoryItemDescriptionUI : MonoBehaviour {
        [SerializeField] private Image itemImage;
        [SerializeField] private TMP_Text title;
        [SerializeField] private TMP_Text description;

        public void Awake() {
            // resests when game starts
            ResetDescription();
        }

        /*---------------------------------------------------------------------
       |  Method Show()
       |
       |  Purpose: Hides itemImage and resets the text for title and description
       |  
       |   Parameters: none
       |
       |  Returns: none
       *-------------------------------------------------------------------*/
        public void ResetDescription() {
            itemImage.gameObject.SetActive(false);
            title.text = "";
            description.text = "";
        }
        /*---------------------------------------------------------------------
        |  Method Show()
        |
        |  Purpose: Shows itemImage and assigns the text for title and description, as
        |           assign the sprite for the current inventorySlot
        |  
        |   Parameters: Sprite sprite = the sprite to fill current slot
        |               string itemName = name of item in current slot
        |               string itemDescription = description of item in current slot
        |
        |  Returns: none
        *-------------------------------------------------------------------*/
        public void SetDescription(Sprite sprite, string itemName, string itemDescription) {
            itemImage.gameObject.SetActive(true);
            itemImage.sprite = sprite;
            title.text = itemName;
            description.text = itemDescription;
        }

    }
}
