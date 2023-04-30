package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Sphere extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
                  int sectorCount,int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        createSphere();


        setupVAOVBO();
    }


    public void createCylinder(float height, float radius) {
        float pi = (float) Math.PI;

        float sectorStep = 2 * pi / sectorCount;
        float stackStep = height / stackCount;
        float sectorAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i) {
            z = i * stackStep - height / 2;
            for (int j = 0; j <= sectorCount; ++j) {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + radius * (float) Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + radius * (float) Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }
//    private void createSphere() {
//        float x, y, z, xy;                          // vertex position
//        float nx, ny, nz, lengthInv = 1.0f / this.radiusZ;    // vertex normal
//        float s, t;                                 // vertex texture coordinates
//
//        float sectorStep = 2 * (float) Math.PI / this.sectorCount;
//        float stackStep = (float) Math.PI / this.stackCount;
//        float sectorAngle, stackAngle;
//
//        for(int i = 0; i <= this.stackCount; ++i) {
//            stackAngle = (float) Math.PI / 2 - i * stackStep;    // starting from pi/2 to -pi/2
//            xy = this.radiusX * (float) Math.cos(stackAngle);             // r * cos(u)
//            z = this.radiusZ * (float) Math.sin(stackAngle);              // r * sin(u)
//
//            // add (sectorCount+1) vertices per stack
//            // the first and last vertices have same position and normal, but different tex coords
//            for(int j = 0; j <= this.sectorCount; ++j) {
//                sectorAngle = j * sectorStep;           // starting from 0 to 2pi
//
//                // vertex position (x, y, z)
//                x = xy * (float) Math.cos(sectorAngle);        // r * cos(u) * cos(v)
//                y = xy * (float) Math.sin(sectorAngle);        // r * cos(u) * sin(v)
//                vertices.add(new Vector3f(x, y, z));
//
//                // normalized vertex normal (nx, ny, nz)
//                nx = x * lengthInv;
//                ny = y * lengthInv;
//                nz = z * lengthInv;
//                normals.add(new Vector3f(nx, ny, nz));
//
//                // vertex texture coordinate (s, t) range between [0, 1]
//                s = (float) j / this.sectorCount;
//                t = (float) i / this.stackCount;
//                textureCoords.add(new Vector2f(s, t));
//            }
//        }
//
//        // generate indices
//        int k1, k2;
//        for(int i = 0; i < this.stackCount; ++i) {
//            k1 = i * (this.sectorCount + 1);     // beginning of current stack
//            k2 = k1 + this.sectorCount + 1;      // beginning of next stack
//
//            for(int j = 0; j < this.sectorCount; ++j, ++k1, ++k2) {
//                // 2 triangles per sector excluding first and last stacks
//                // k1 => k2 => k1+1
//                if(i != 0) {
//                    indices.add(k1);
//                    indices.add(k2);
//                    indices.add(k1 + 1);
//                }
//
//                // k1+1 => k2 => k2+1
//                if(i != (this.stackCount-1)) {
//                    indices.add(k1 + 1);
//                    indices.add(k2);
//                    indices.add(k2 + 1);
//                }
//            }
//        }
//    }
    public void createSphere(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
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

}
