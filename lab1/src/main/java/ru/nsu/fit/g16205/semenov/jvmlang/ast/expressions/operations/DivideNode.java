package ru.nsu.fit.g16205.semenov.jvmlang.ast.expressions.operations;

import org.objectweb.asm.MethodVisitor;
import ru.nsu.fit.g16205.semenov.jvmlang.Type;
import ru.nsu.fit.g16205.semenov.jvmlang.asm.Context;
import ru.nsu.fit.g16205.semenov.jvmlang.ast.expressions.ExpressionNode;

import static org.objectweb.asm.Opcodes.IDIV;
import static ru.nsu.fit.g16205.semenov.jvmlang.Type.INTEGER;

public class DivideNode extends BinaryOperationNode {

    public DivideNode(ExpressionNode left, ExpressionNode right) {
        super("/", left, right);
    }

    @Override
    public Type getType(Context context) {
        if (INTEGER.equals(getRight().getType(context)) && INTEGER.equals(getLeft().getType(context)))
            return INTEGER;
        throw new IllegalStateException("Invalid arguments types");
    }

    @Override
    public void write(MethodVisitor mv, Context context) {
        // TODO order of application??
        getLeft().write(mv, context);
        getRight().write(mv, context);
        mv.visitInsn(IDIV);
    }
}
