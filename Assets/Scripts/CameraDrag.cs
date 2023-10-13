using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class CameraDrag : MonoBehaviour {
    [SerializeField] private Vector3 minBound, maxBound;
    private Vector3 targetPos;
    [SerializeField] private Camera mainCamera;
    private float height;
    private float width;
    [SerializeField] private Transform objectToFollow;
    [SerializeField] Vector3 offset;
    [Range(1, 10)][SerializeField] private float smoothFactor;


    private void FixedUpdate() {
        Follow();
    }
    private void Follow() {
        // follows the desired object
        Vector3 targetPos = objectToFollow.position + offset;
        // sets bounds of camera
        Vector3 boundPos = new Vector3(
            Mathf.Clamp(targetPos.x, minBound.x, maxBound.x),
            Mathf.Clamp(targetPos.y, minBound.y, maxBound.y),
            Mathf.Clamp(targetPos.z, minBound.z, maxBound.z));
        // smooths the movent of the camera:
        // 1 = delayed, 10= 1 to 1 
        Vector3 smoothPos = Vector3.Lerp(transform.position, boundPos, smoothFactor * Time.deltaTime);
        transform.position = smoothPos;

    }
}
