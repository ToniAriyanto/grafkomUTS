package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class halfCircle extends Sphere {
    int stackCount;
    int sectorCount;
    private Float radiusX;
    private Float radiusY;
    private Float radiusZ;
    public halfCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY, radiusZ, sectorCount, stackCount);
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        halfCircle();
        setupVAOVBO();
    }
    public void halfCircle(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount/2; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }

//    public void createHalfCircle(){
//        vertices.clear();
//        for(float i = 0;i<=180;i+=0.1){
//            double rad = degToRad(i);
//            Float x = (float) (centerPoint.get(0)+Math.cos(rad)*radiusX);
//            Float y = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
//            Float z = 0.0f;
//            vertices.add(new Vector3f(x,y,z));
//        }
//    }
//    private void createCircle(List<Float> centerPoint){
//        vertices.clear();
//        for(float i = 0;i<360;i+=0.1){
//            double rad = degToRad(i);
//            Float x = (float) (centerPoint.get(0)+Math.cos(rad)*radiusX);
//            Float y = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
//            Float z = centerPoint.get(2) + radiusZ * (i/360);
//            vertices.add(new Vector3f(x,y,z));
//        }
//    }
}
