using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Inventory.Model {
    [CreateAssetMenu]
    public class CardSO : ScriptableObject {

        [field: SerializeField] public bool isStackable { get; set; }
        private int ID => GetInstanceID();
        [field: SerializeField] private int maxStackSize { get; set; } = 1;
        [field: SerializeField] private string cardName { get; set; }
        [field: SerializeField][field: TextArea] public string description { get; set; }
        [field: SerializeField] private Sprite image { get; set; }

        [field:SerializeField]  private List<CardParameter> parameterList { get; set; }

        public int GetID() {
            return ID;
        }
        public int GetMaxStackSize() {
            return maxStackSize;
        }
        public string GetName() {
            return cardName;
        }
        public Sprite GetImage() {
            return image;
        }
        public List<CardParameter> GetList() {
            return parameterList;
        }       

    }

    [Serializable]
    public struct CardParameter: IEquatable<CardParameter> {
        public CardParameterSO cardParameter;
        public float val;

        public bool Equals(CardParameter other) {
            return other.cardParameter == cardParameter;
        }
    }
}
