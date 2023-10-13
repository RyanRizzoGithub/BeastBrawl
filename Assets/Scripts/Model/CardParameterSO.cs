using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Inventory.Model {
    [CreateAssetMenu]
    public class CardParameterSO : ScriptableObject {
        [field:SerializeField] string parameterName { get; set; }
    }
}
