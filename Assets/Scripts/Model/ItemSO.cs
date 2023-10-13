using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Inventory.Model {
    [CreateAssetMenu]
    public abstract class ItemSO : ScriptableObject {

        [field: SerializeField] public bool isStackable { get; set; }
        private int ID => GetInstanceID();
        [field: SerializeField] private int maxStackSize { get; set; } = 1;
        [field: SerializeField] private string itemName { get; set; }
        [field: SerializeField][field: TextArea] public string description { get; set; }
        [field: SerializeField] private Sprite image { get; set; }

        [field:SerializeField]  private List<ItemParameter> parameterList { get; set; }

        public int GetID() {
            return ID;
        }
        public int GetMaxStackSize() {
            return maxStackSize;
        }
        public string GetName() {
            return itemName;
        }
        public Sprite GetImage() {
            return image;
        }
        public List<ItemParameter> GetList() {
            return parameterList;
        }       

    }

    [Serializable]
    public struct ItemParameter: IEquatable<ItemParameter> {
        public ItemParameterSO itemParameter;
        public float val;

        public bool Equals(ItemParameter other) {
            return other.itemParameter == itemParameter;
        }
    }
}
