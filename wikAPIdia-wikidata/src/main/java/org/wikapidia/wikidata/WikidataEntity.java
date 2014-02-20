package org.wikapidia.wikidata;

import org.wikapidia.core.lang.Language;

import java.io.Serializable;
import java.util.*;

/**
 * @author Shilad Sen
 */
public class WikidataEntity implements Serializable {
    public static enum Type {
        ITEM('Q'), PROPERTY('P');

        public char code;
        Type(char code) { this.code = code; }

        public static Type getByCode(char code) {
            code = Character.toUpperCase(code);
            for (Type type : values()) {
                if (type.code == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown type code: " + code);
        }
    }

    private Type type;
    private int id;

    private Map<Language, String> labels = new LinkedHashMap<Language, String>();
    private Map<Language, String> descriptions = new LinkedHashMap<Language, String>();
    private Map<Language, List<String>> aliases = new LinkedHashMap<Language, List<String>>();
    private List<WikidataStatement> statements = new ArrayList<WikidataStatement>();

    public WikidataEntity(Type type, int id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getStringId() {
        return type.code + "" + id;
    }

    public Map<Language, String> getLabels() {
        return labels;
    }

    public Map<Language, String> getDescriptions() {
        return descriptions;
    }

    public Map<Language, List<String>> getAliases() {
        return aliases;
    }

    public List<WikidataStatement> getStatements() {
        return statements;
    }

    public Map<String, List<WikidataStatement>> getStatementsInLanguage(Language language) {
        Map<String, List<WikidataStatement>> inLang = new HashMap<String, List<WikidataStatement>>();
        for (WikidataStatement s : statements) {
            String label = s.getProperty().getLabels().get(language);
            if (label != null) {
                if (!inLang.containsKey(label)) {
                    inLang.put(label, new ArrayList<WikidataStatement>());
                }
                inLang.get(label).add(s);
            }
        }
        return inLang;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + 37 * id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WikidataEntity that = (WikidataEntity) o;

        if (id != that.id) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public String toString() {
        String name;
        Language en = Language.getByLangCode("en");
        if (labels.containsKey(en)) {
            name = labels.get(en);
        } else if (labels.isEmpty()) {
            name = "unknown";
        } else {
            name = labels.values().iterator().next();
        }
        return "WikidataEntity{" +
                "type=" + type +
                ", id=" + id +
                ", name=" + name +
                '}';
    }
}
