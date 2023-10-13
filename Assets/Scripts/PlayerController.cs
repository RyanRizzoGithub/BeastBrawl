using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    [SerializeField] private float moveSpeed;
    private Vector2 forceToAplly;
    [SerializeField] private float forceDamping;
    private Vector2 playerInput;
    private Vector2 moveForce;
    private Animator playerAnimator;
    [SerializeField] private LayerMask solidObjectLayer;
    private bool isMoving;
    public HealthSystem healthSystem;
    public Rigidbody2D rb;
    [SerializeField] private CharacterStats characterStats;
    private bool isInventoryMenuOpen;
    private void Awake() {
        playerAnimator = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
        moveSpeed = 5;
        forceDamping = 1.2f;
        healthSystem = new HealthSystem(100);
        isInventoryMenuOpen = false;

    }

    private void Update() {
        isInventoryOpen();
    }

    private void FixedUpdate() {
        MovePlayer();
        
        

    }
    private void MovePlayer() {
        // normalize player input so dialoganal is same spped as vert and horiziontal
        playerInput = (new Vector2(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical")) * Time.deltaTime).normalized;

        // applies current moveSpeed
        moveForce = (playerInput * moveSpeed);
        // allows for knockback from other physics obejcts
        moveForce += forceToAplly;
        forceToAplly /= forceDamping;
        if (Mathf.Abs(forceToAplly.x) <= 0.01f && Mathf.Abs(forceToAplly.y) <= 0.0f) {
            forceToAplly = Vector2.zero;
        }
        rb.velocity = moveForce;

        // determines if the player is moving or not
        if (rb.velocity != Vector2.zero) {
            //gets input values for idle animation direction
            playerAnimator.SetFloat("moveX", Input.GetAxisRaw("Horizontal"));
            playerAnimator.SetFloat("moveY", Input.GetAxisRaw("Vertical"));

            isMoving = true;

        }
        else {
            isMoving = false;
        }
        playerAnimator.SetBool("isMoving", isMoving);
    }

    private void isInventoryOpen() {

        if (Input.GetKeyUp(KeyCode.I)) {

            isInventoryMenuOpen = !isInventoryMenuOpen;
            //Debug.Log(isInventoryMenuOpen);
        }

    }

    public bool GetIsInentoryOpen() {
        return isInventoryMenuOpen;
    }

    public void OnCollisionEnter2D(Collision2D collision) {
        Debug.Log(collision.gameObject.name);
    }


    public void OnTriggerEnter2D(Collider2D collision) {
        Debug.Log(collision.gameObject.tag);
        if (collision.gameObject.tag == "Healing") {
            Debug.Log("--Heal");
            characterStats.Heal(10);
        }
        else if (collision.gameObject.tag == "Damaging") {
            Debug.Log("---Damaging");
            characterStats.Damage(10);
        }
    }
}
