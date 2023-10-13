using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[CreateAssetMenu]
public class CharacterHealthStatSO : CharcterStatModifierSO
{
    public override void AffectCharacter(GameObject character, float val) {
      // Health health = character.ge
      // get chracters health and modifly it
      CharacterStats characterStats = character.GetComponent<CharacterStats>();
        if(characterStats != null ) {
            characterStats.Heal(val);
        }
    }

}
