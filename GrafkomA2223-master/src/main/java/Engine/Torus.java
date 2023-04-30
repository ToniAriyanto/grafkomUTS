package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class Torus extends Sphere{
    int stackCount;
    int sectorCount;
    private Float radiusX;
    private Float radiusY;
    private Float radiusZ;
    public Torus(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY, radiusZ, sectorCount, stackCount);
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        createTorus(centerPoint);
        setupVAOVBO();
    }
    private void createTorus(List<Float> centerPoint){
        vertices.clear();
        for(float i = 0;i<360;i+=(360f/sectorCount)){
            double radI = degToRad(i);
            for(float j = 0; j < 360; j+=(360f/stackCount)){
                double radJ = degToRad(j);
                Float x = (float) ((centerPoint.get(0) + (radiusX + radiusY*Math.cos(radJ))*Math.cos(radI)));
                Float y = (float) ((centerPoint.get(1) + (radiusX + radiusY*Math.cos(radJ))*Math.sin(radI)));
                Float z = (float) (centerPoint.get(2) + radiusZ*Math.sin(radJ));
                vertices.add(new Vector3f(x,y,z));
            }
        }
    }
}
