package org.KonohaScript;

import java.util.ArrayList;

public class KParam {
	public final static int	MAX					= 16;
	public final static int	VariableParamSize	= -1;
	public int				ReturnSize;
	public KClass[]			Types;

	public KParam(int DataSize, KClass ParamData[]) {
		this.ReturnSize = 1;
		this.Types = new KClass[DataSize];
		System.arraycopy(ParamData, 0, this.Types, 0, DataSize);
	}

	public static KParam ParseOf(KNameSpace ns, String TypeList) {
		ArrayList<KToken> BufferList = ns.Tokenize(TypeList, 0);
		int next = BufferList.size();
		ns.PreProcess(BufferList, 0, next, BufferList);
		KClass[] ParamData = new KClass[KParam.MAX];
		int i, DataSize = 0;
		for(i = next; i < BufferList.size(); i++) {
			KToken Token = BufferList.get(i);
			if(Token.ResolvedObject instanceof KClass) {
				ParamData[DataSize] = (KClass) Token.ResolvedObject;
				DataSize++;
				if(DataSize == KParam.MAX)
					break;
			}
		}
		return new KParam(DataSize, ParamData);
	}

	public final int GetParamSize() {
		return this.Types.length - this.ReturnSize;
	};

	public final boolean Match(KParam Other) {
		int ParamSize = Other.GetParamSize();
		if(ParamSize == GetParamSize()) {
			for(int i = ReturnSize; i < Types.length; i++) {
				if(Types[i] != Other.Types[i]) return false;
			}
			return true;
		}
		return false;
	}

	// public boolean Accept(int ParamSize, KClass[] Types) {
	// if(ParamTypes. == ParamSize) {
	// for(int i = 1; i < ParamSize; i++) {
	// if(!ParamTypes[i].Accept(Types[i])) return false;
	// }
	// return true;
	// }
	// return false;
	// }
	// return true;
	// }

}
