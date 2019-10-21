package com.nwar.individual.eyetracking

import android.content.Context
import android.graphics.PointF
import android.util.Log
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.face.Face
import com.google.android.gms.vision.face.Landmark

class FaceTracker (context : Context, val overlay : GraphicOverlay) : Tracker<Face>() {
    val graphic = FaceGraphic(overlay,context,true)
    val landmarkMap = HashMap<Int,PointF>()

    override fun onMissing(p0: Detector.Detections<Face>?) {
        super.onMissing(p0)
        overlay.remove(graphic)
    }

    override fun onDone() {
        super.onDone()
        overlay.remove(graphic)
    }

    override fun onUpdate(p0: Detector.Detections<Face>?, p1: Face?) {
        super.onUpdate(p0, p1)
        overlay.add(graphic)
        graphic.update(p1?.toFaceData())
        Log.e("faceposition","x : " + p1?.position?.x?.toString() + " y : " + p1?.position?.y?.toString())
    }

    fun Face.toFaceData() : FaceData{
        val faceData = FaceData()
        faceData.setPosition(this.getPosition());
        faceData.setWidth(this.getWidth());
        faceData.setHeight(this.getHeight());

        // Get the positions of facial landmarks.
        updatePreviousLandmarkPositions(this);
        faceData.setLeftEyePosition(getLandmarkPosition(this, Landmark.LEFT_EYE))
        faceData.setRightEyePosition(getLandmarkPosition(this, Landmark.RIGHT_EYE))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.LEFT_CHEEK))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.RIGHT_CHEEK))
        faceData.setNoseBasePosition(getLandmarkPosition(this, Landmark.NOSE_BASE))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.LEFT_EAR))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.LEFT_EAR_TIP))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.RIGHT_EAR))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.RIGHT_EAR_TIP))
        faceData.setMouthLeftPosition(getLandmarkPosition(this, Landmark.LEFT_MOUTH))
        faceData.setMouthBottomPosition(getLandmarkPosition(this, Landmark.BOTTOM_MOUTH))
        faceData.setMouthRightPosition(getLandmarkPosition(this, Landmark.RIGHT_MOUTH))
        faceData.eulerY = this.eulerY
        faceData.eulerZ = this.eulerZ
        return faceData
    }
    private fun updatePreviousLandmarkPositions(face: Face) {
        for (landmark in face.landmarks) {
            val position = landmark.position
            val xProp = (position.x - face.position.x) / face.width
            val yProp = (position.y - face.position.y) / face.height
            landmarkMap.put(landmark.type, PointF(xProp, yProp))
        }
    }

    private fun getLandmarkPosition(face: Face, landmarkId: Int): PointF? {
        for (landmark in face.landmarks) {
            if (landmark.type == landmarkId) {
                return landmark.position
            }
        }

        val landmarkPosition = landmarkMap.get(landmarkId) ?: return null

        val x = face.position.x + landmarkPosition.x * face.width
        val y = face.position.y + landmarkPosition.y * face.height
        return PointF(x, y)
    }
}