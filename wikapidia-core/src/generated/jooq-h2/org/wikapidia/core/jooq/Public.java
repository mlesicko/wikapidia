/**
 * This class is generated by jOOQ
 */
package org.wikapidia.core.jooq;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.0"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked" })
public class Public extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = 534455526;

	/**
	 * The singleton instance of <code>PUBLIC</code>
	 */
	public static final Public PUBLIC = new Public();

	/**
	 * No further instances allowed
	 */
	private Public() {
		super("PUBLIC");
	}

	@Override
	public final java.util.List<org.jooq.Sequence<?>> getSequences() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final java.util.List<org.jooq.Sequence<?>> getSequences0() {
		return java.util.Arrays.<org.jooq.Sequence<?>>asList(
			org.wikapidia.core.jooq.Sequences.SYSTEM_SEQUENCE_D876D846_E193_41C1_A648_BC368B298251,
			org.wikapidia.core.jooq.Sequences.SYSTEM_SEQUENCE_EE746FDB_CFB0_400C_B1B4_5E67ACC00990,
			org.wikapidia.core.jooq.Sequences.SYSTEM_SEQUENCE_FC292E36_4858_46C7_A210_7709DBD60141);
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.wikapidia.core.jooq.tables.Article.ARTICLE,
			org.wikapidia.core.jooq.tables.Link.LINK,
			org.wikapidia.core.jooq.tables.Concept.CONCEPT,
			org.wikapidia.core.jooq.tables.ArticleConcept.ARTICLE_CONCEPT,
			org.wikapidia.core.jooq.tables.LocalPage.LOCAL_PAGE);
	}
}
