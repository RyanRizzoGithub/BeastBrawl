using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterStats : MonoBehaviour
{
    [SerializeField] private float maxHealth;
   // [SerializeField] private float curHealth;

    private float curHealth;
    [SerializeField] private HealthBar healthBar;

    private void Start() {
        curHealth = maxHealth;
        healthBar.SetSliderMax(maxHealth);
    }
    public void Damage(float damageNum) {
        curHealth -= damageNum;
        healthBar.SetSlider(curHealth);
    }

    public void Heal(float healNum) {
        float amountToHeal = (maxHealth - curHealth);
        if (healNum+curHealth>=maxHealth) {
            curHealth =maxHealth;
        }
        else {
            curHealth += healNum;
        }
        
        healthBar.SetSlider(curHealth);
    }
}
