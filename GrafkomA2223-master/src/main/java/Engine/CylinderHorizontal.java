package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class CylinderHorizontal extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;

    float height;
    public CylinderHorizontal(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, float radiusZ, int stackCount, int sectorCount, float height) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.height = height;

        createCylinder(height);
        setupVAOVBO();
    }
    public void createCylinder(float height) {
        float pi = (float) Math.PI;

        float sectorStep = 2 * pi / sectorCount;
        float stackStep = height / stackCount;
        float sectorAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i) {
            z = i * stackStep - height / 2;
            for (int j = 0; j <= sectorCount; ++j) {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + radiusX * (float) Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + radiusY * (float) Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }


//        for (int i = 0; i <= stackCount; ++i) {
//            y = i * stackStep - height / 2;
//            for (int j = 0; j <= sectorCount; ++j) {
//                sectorAngle = j * sectorStep;
//                Vector3f temp_vector = new Vector3f();
//                temp_vector.x = centerPoint.get(0) + radiusX * (float) Math.cos(sectorAngle);
//                temp_vector.y = centerPoint.get(1) + y;
//                temp_vector.z = centerPoint.get(2) + radiusZ * (float) Math.sin(sectorAngle);
//                vertices.add(temp_vector);
//            }
//        }
    }
}
