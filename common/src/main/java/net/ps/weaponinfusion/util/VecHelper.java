package net.ps.weaponinfusion.util;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.phys.Vec3;

public class VecHelper {
    private VecHelper() {
        /* This utility class should not be instantiated */
    }

    public static final Vec3 CENTER_OF_ORIGIN = new Vec3(0.5F, 0.5F, 0.5F);

    public static Vec3 rotate(Vec3 vec, Vec3 rotationVec) {
        return rotate(vec, rotationVec.x, rotationVec.y, rotationVec.z);
    }

    public static Vec3 rotate(Vec3 vec, double xRot, double yRot, double zRot) {
        return rotate(rotate(rotate(vec, xRot, Axis.X), yRot, Axis.Y), zRot, Axis.Z);
    }

    public static Vec3 rotateCentered(Vec3 vec, double deg, Direction.Axis axis) {
        Vec3 shift = getCenterOf(BlockPos.ZERO);
        return rotate(vec.subtract(shift), deg, axis).add(shift);
    }

    public static Vec3 rotate(Vec3 vec, double deg, Direction.Axis axis) {
        if (deg == 0.0F) {
            return vec;
        } else if (vec == Vec3.ZERO) {
            return vec;
        } else {
            float angle = (float) (deg / 180.0F * Math.PI);
            double sin = Mth.sin(angle);
            double cos = Mth.cos(angle);
            double x = vec.x;
            double y = vec.y;
            double z = vec.z;
            return switch (axis) {
                case Axis.X -> new Vec3(x, y * cos - z * sin, z * cos + y * sin);
                case Axis.Y -> new Vec3(x * cos + z * sin, y, z * cos - x * sin);
                default -> axis == Axis.Z ? new Vec3(x * cos - y * sin, y * cos + x * sin, z) : vec;
            };
        }
    }

    public static Vec3 mirrorCentered(Vec3 vec, Mirror mirror) {
        Vec3 shift = getCenterOf(BlockPos.ZERO);
        return mirror(vec.subtract(shift), mirror).add(shift);
    }

    public static Vec3 mirror(Vec3 vec, Mirror mirror) {
        if (mirror == Mirror.NONE) {
            return vec;
        } else if (vec == Vec3.ZERO) {
            return vec;
        } else {
            double x = vec.x;
            double y = vec.y;
            double z = vec.z;
            if (mirror == Mirror.LEFT_RIGHT) {
                return new Vec3(x, y, -z);
            } else {
                return mirror == Mirror.FRONT_BACK ? new Vec3(-x, y, z) : vec;
            }
        }
    }

    public static Vec3 lookAt(Vec3 vec, Vec3 fwd) {
        fwd = fwd.normalize();
        Vec3 up = new Vec3(0.0F, 1.0F, 0.0F);
        double dot = fwd.dot(up);
        if (Math.abs(dot) > 0.999) {
            up = new Vec3(0.0F, 0.0F, dot > 0.0F ? 1.0F : -1.0F);
        }

        Vec3 right = fwd.cross(up).normalize();
        up = right.cross(fwd).normalize();
        double x = vec.x * right.x + vec.y * up.x + vec.z * fwd.x;
        double y = vec.x * right.y + vec.y * up.y + vec.z * fwd.y;
        double z = vec.x * right.z + vec.y * up.z + vec.z * fwd.z;
        return new Vec3(x, y, z);
    }

    public static boolean isVecPointingTowards(Vec3 vec, Direction direction) {
        return Vec3.atLowerCornerOf(direction.getUnitVec3i()).dot(vec.normalize()) > 0.125F;
    }

    public static Vec3 getCenterOf(Vec3i pos) {
        return pos.equals(Vec3i.ZERO) ? CENTER_OF_ORIGIN : Vec3.atLowerCornerOf(pos).add(0.5F, 0.5F, 0.5F);
    }

    public static Vec3 offsetRandomly(Vec3 vec, RandomSource r, float radius) {
        return new Vec3(vec.x + ((r.nextFloat() - 0.5F) * 2.0F * radius), vec.y + ((r.nextFloat() - 0.5F) * 2.0F * radius), vec.z + ((r.nextFloat() - 0.5F) * 2.0F * radius));
    }

    public static Vec3 axisAlignedPlaneOf(Vec3 vec) {
        vec = vec.normalize();
        return (new Vec3(1.0F, 1.0F, 1.0F)).subtract(Math.abs(vec.x), Math.abs(vec.y), Math.abs(vec.z));
    }

    public static Vec3 axisAlignedPlaneOf(Direction face) {
        return axisAlignedPlaneOf(Vec3.atLowerCornerOf(face.getUnitVec3i()));
    }

    public static ListTag writeNBT(Vec3 vec) {
        ListTag nbtList = new ListTag();
        nbtList.add(DoubleTag.valueOf(vec.x));
        nbtList.add(DoubleTag.valueOf(vec.y));
        nbtList.add(DoubleTag.valueOf(vec.z));
        return nbtList;
    }

    public static CompoundTag writeNBTCompound(Vec3 vec) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.put("V", writeNBT(vec));
        return compoundTag;
    }

    public static Vec3 readNBT(ListTag list) {
        return list.isEmpty() ? Vec3.ZERO : new Vec3(list.getDoubleOr(0, 0.0F), list.getDoubleOr(1, 0.0F), list.getDoubleOr(2, 0.0F));
    }

    public static Vec3 readNBTCompound(CompoundTag nbt) {
        return readNBT(nbt.getListOrEmpty("V"));
    }

    public static void write(Vec3 vec, FriendlyByteBuf buffer) {
        buffer.writeDouble(vec.x);
        buffer.writeDouble(vec.y);
        buffer.writeDouble(vec.z);
    }

    public static Vec3 read(FriendlyByteBuf buffer) {
        return new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    public static Vec3 voxelSpace(double x, double y, double z) {
        return (new Vec3(x, y, z)).scale(0.0625F);
    }

    public static int getCoordinate(Vec3i pos, Direction.Axis axis) {
        return axis.choose(pos.getX(), pos.getY(), pos.getZ());
    }

    public static float getCoordinate(Vec3 vec, Direction.Axis axis) {
        return (float) axis.choose(vec.x, vec.y, vec.z);
    }

    public static boolean onSameAxis(BlockPos pos1, BlockPos pos2, Direction.Axis axis) {
        if (!pos1.equals(pos2)) {
            for (Axis otherAxis : Axis.values()) {
                if (axis != otherAxis && getCoordinate(pos1, otherAxis) != getCoordinate(pos2, otherAxis)) {
                    return false;
                }
            }

        }
        return true;
    }

    public static Vec3 clamp(Vec3 vec, float maxLength) {
        return vec.lengthSqr() > (maxLength * maxLength) ? vec.normalize().scale(maxLength) : vec;
    }

    public static Vec3 lerp(float p, Vec3 from, Vec3 to) {
        return from.add(to.subtract(from).scale(p));
    }

    public static Vec3 slerp(float p, Vec3 from, Vec3 to) {
        double theta = Math.acos(from.dot(to));
        return from.scale(Mth.sin(1.0F - p) * theta).add(to.scale(Mth.sin((theta * p)))).scale((1.0F / Mth.sin(theta)));
    }

    public static Vec3 clampComponentWise(Vec3 vec, float maxLength) {
        return new Vec3(Mth.clamp(vec.x, (-maxLength), maxLength), Mth.clamp(vec.y, (-maxLength), maxLength), Mth.clamp(vec.z, (-maxLength), maxLength));
    }

    public static Vec3 componentMin(Vec3 vec1, Vec3 vec2) {
        return new Vec3(Math.min(vec1.x, vec2.x), Math.min(vec1.y, vec2.y), Math.min(vec1.z, vec2.z));
    }

    public static Vec3 componentMax(Vec3 vec1, Vec3 vec2) {
        return new Vec3(Math.max(vec1.x, vec2.x), Math.max(vec1.y, vec2.y), Math.max(vec1.z, vec2.z));
    }

    public static Vec3 project(Vec3 vec, Vec3 ontoVec) {
        return ontoVec.equals(Vec3.ZERO) ? Vec3.ZERO : ontoVec.scale(vec.dot(ontoVec) / ontoVec.lengthSqr());
    }


    public static Vec3 intersectSphere(Vec3 origin, Vec3 lineDirection, Vec3 sphereCenter, double radius) {
        if (lineDirection.equals(Vec3.ZERO)) {
            return null;
        } else {
            if (lineDirection.lengthSqr() != 1.0F) {
                lineDirection = lineDirection.normalize();
            }

            Vec3 diff = origin.subtract(sphereCenter);
            double lineDotDiff = lineDirection.dot(diff);
            double delta = lineDotDiff * lineDotDiff - (diff.lengthSqr() - radius * radius);
            if (delta < 0.0F) {
                return null;
            } else {
                double t = -lineDotDiff + Math.sqrt(delta);
                return origin.add(lineDirection.scale(t));
            }
        }
    }

    public static Vec3 bezier(Vec3 p1, Vec3 p2, Vec3 q1, Vec3 q2, float t) {
        Vec3 v1 = lerp(t, p1, q1);
        Vec3 v2 = lerp(t, q1, q2);
        Vec3 v3 = lerp(t, q2, p2);
        Vec3 inner1 = lerp(t, v1, v2);
        Vec3 inner2 = lerp(t, v2, v3);
        return lerp(t, inner1, inner2);
    }

    public static Vec3 bezierDerivative(Vec3 p1, Vec3 p2, Vec3 q1, Vec3 q2, float t) {
        return p1.scale((-3.0F * t * t + 6.0F * t - 3.0F)).add(q1.scale((9.0F * t * t - 12.0F * t + 3.0F))).add(q2.scale((-9.0F * t * t + 6.0F * t))).add(p2.scale((3.0F * t * t)));
    }

    
    public static double[] intersectRanged(Vec3 p1, Vec3 q1, Vec3 p2, Vec3 q2, Direction.Axis plane) {
        Vec3 pDiff = p2.subtract(p1);
        Vec3 qDiff = q2.subtract(q1);
        double[] intersect = intersect(p1, q1, pDiff.normalize(), qDiff.normalize(), plane);

        if (intersect[0] >= 0.0F && intersect[1] >= 0.0F) {
            return intersect[0] * intersect[0] <= pDiff.lengthSqr() && intersect[1] * intersect[1] <= qDiff.lengthSqr() ? intersect : null;
        } else {
            return new double[0];
        }
    }

    
    public static double[] intersect(Vec3 p1, Vec3 p2, Vec3 r, Vec3 s, Direction.Axis plane) {
        if (plane == Axis.X) {
            p1 = new Vec3(p1.y, 0.0F, p1.z);
            p2 = new Vec3(p2.y, 0.0F, p2.z);
            r = new Vec3(r.y, 0.0F, r.z);
            s = new Vec3(s.y, 0.0F, s.z);
        }

        if (plane == Axis.Z) {
            p1 = new Vec3(p1.x, 0.0F, p1.y);
            p2 = new Vec3(p2.x, 0.0F, p2.y);
            r = new Vec3(r.x, 0.0F, r.y);
            s = new Vec3(s.x, 0.0F, s.y);
        }

        Vec3 qminusp = p2.subtract(p1);
        double rcs = r.x * s.z - r.z * s.x;
        if (Mth.equal(rcs, 0.0F)) {
            return new double[0];
        } else {
            Vec3 rdivrcs = r.scale(1.0F / rcs);
            Vec3 sdivrcs = s.scale(1.0F / rcs);
            double t = qminusp.x * sdivrcs.z - qminusp.z * sdivrcs.x;
            double u = qminusp.x * rdivrcs.z - qminusp.z * rdivrcs.x;
            return new double[]{t, u};
        }
    }

    public static double alignedDistanceToFace(Vec3 pos, BlockPos blockPos, Direction face) {
        Direction.Axis axis = face.getAxis();
        return Math.abs(getCoordinate(pos, axis) - (blockPos.get(axis) + (face.getAxisDirection() == AxisDirection.POSITIVE ? 1 : 0)));
    }
}
