package test

class Author {
    String name
    Long age
    Boolean famous = false

    // This should get masked globally
    String ssn = "123-456-7890"

    Date dateCreated
    Date lastUpdated
    String lastUpdatedBy

    String handlerCalled = ""
    static transients = ['handlerCalled']

    static hasMany = [books: Book]

    static auditable = true

    static constraints = {
        lastUpdatedBy nullable: true
    }

    // Event handlers
    def onSave = { newMap ->
        assert newMap
        handlerCalled += "onSave"
    }

    def onChange = { oldMap, newMap ->
        assert oldMap
        assert newMap
        handlerCalled += "onChange"
    }

    def onDelete = { oldMap ->
        assert oldMap
        handlerCalled += "onDelete"
    }
}
