package org.KonohaScript.SyntaxTree;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.KonohaScript.KClass;
import org.KonohaScript.CodeGen.ASTVisitor;

public class MethodCallNode extends TypedNode implements CallableNode {
	public ArrayList<TypedNode> Params; /* [this, arg1, arg2, ...] */
	Method Mtd;

	/* call self.Method(arg1, arg2, ...) */
	public MethodCallNode(KClass TypeInfo, Method Mtd) {
		super(TypeInfo);
		this.Mtd = Mtd;
		this.Params = new ArrayList<TypedNode>();
	}

	@Override
	public void Append(TypedNode Expr) {
		this.Params.add(Expr);
	}

	@Override
	public boolean Evaluate(ASTVisitor Visitor) {
		Visitor.EnterMethodCall(this);
		for (TypedNode Node : this.Params) {
			Visitor.Visit(Node);
		}
		return Visitor.ExitMethodCall(this);
	}
}