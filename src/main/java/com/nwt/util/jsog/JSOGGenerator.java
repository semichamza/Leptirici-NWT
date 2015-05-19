package com.nwt.util.jsog;



import com.fasterxml.jackson.annotation.ObjectIdGenerator;

/**
 * Use this as an object id generator and your class will serialize as jsog.
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class JSOGGenerator extends ObjectIdGenerator<JSOGRef> {

	private static final long serialVersionUID = 1L;

	protected transient int _nextValue;
    protected final Class<?> _scope;

    protected JSOGGenerator() { this(null, -1); }

    protected JSOGGenerator(Class<?> scope, int nextValue) {
        _scope = scope;
        _nextValue = nextValue;
    }

    @Override
    public Class<?> getScope() {
        return _scope;
    }

    @Override
    public boolean canUseFor(ObjectIdGenerator<?> gen) {
        return (gen.getClass() == getClass()) && (gen.getScope() == _scope);
    }

	@Override
	public ObjectIdGenerator<JSOGRef> forScope(Class<?> scope) {
        return (_scope == scope) ? this : new JSOGGenerator(scope, _nextValue);
	}

	@Override
	public ObjectIdGenerator<JSOGRef> newForSerialization(Object context) {
        return new JSOGGenerator(_scope, 1);
	}

	@Override
	public IdKey key(Object key) {
        return new IdKey(getClass(), _scope, key);
	}

	@Override
	public JSOGRef generateId(Object nwtEntity) {
        int id;
        try {
            id= Integer.valueOf(nwtEntity.getClass().getMethod("getId").invoke(nwtEntity).toString());
        } catch (Exception e) {
            id=_nextValue;
            ++_nextValue;
        }

        return new JSOGRef(id);
	}


    public boolean maySerializeAsObject() {
        return true;
    }


    public boolean isValidReferencePropertyName(String name, Object parser) {
        return JSOGRef.REF_KEY.equals(name);
    }
}
