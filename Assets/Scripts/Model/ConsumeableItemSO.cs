using System;
using System.Collections;
using System.Collections.Generic;
using System.Net.NetworkInformation;
using UnityEngine;
using UnityEngine.XR;

namespace Inventory.Model {
    [CreateAssetMenu]
    public class ConsumeableItemSO : ItemSO, IDestroyableItem, IItemAction {
       
        [SerializeField] private List<ModifierData> modifiersData = new List<ModifierData>();

        public string ActionName => "Consume";
        [field: SerializeField]  public AudioClip actionSFX { get; private set; }
        /*---------------------------------------------------------------------
         *  Method PerformAction(GameObject character)
         *
         *  Purpose: Perfrosm the actios of a character
         *
         * Parameters: GameObject character = chatcer whos actrins are preformed
         *
         * Returns: none
         *-------------------------------------------------------------------*/
        public bool PerformAction(GameObject character,List<ItemParameter> itemState = null) {
           // Debug.Log("using");
            foreach (ModifierData data in modifiersData) {
                data.statModifier.AffectCharacter(character, data.value);
            }
            return true;
        }
    }
    /*---------------------------------------------------------------------
       *  Interface IDestroyableItem
       *
       *  Purpose: Allows items to be destroyed when selected
       *
       *-------------------------------------------------------------------*/
    public interface IDestroyableItem {

    }

    public interface IItemAction {
        public string ActionName { get; }
        public AudioClip actionSFX { get; }
        bool PerformAction(GameObject character, List<ItemParameter> itemState);
    }

    [Serializable]
    public class ModifierData {
        public CharcterStatModifierSO statModifier;
        public float value;
    }
}