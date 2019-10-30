package uk.lukewarlow.console.menu.kotlin

class MenuItem
{
    var id: Long = 0
        private set

    var description: String = ""
        private set

    private var menu: AbstractMenu? = null

    private var action: (()->Unit)? = null

    var isVisible: Boolean = false
        private set

    private var isExitOption: Boolean = false

    internal constructor(id: Long)
    {
        this.id = id
    }

    constructor(id: Long, description: String, action: (()->Unit)? = null)
    {
        this.id = id
        this.description = description
        this.action = action
        show()
    }

    constructor(id: Long, description: String, menu: AbstractMenu)
    {
        this.id = id
        this.description = description
        this.menu = menu
        show()
    }

    fun hide(): MenuItem
    {
        isVisible = false
        return this
    }

    fun show(): MenuItem
    {
        isVisible = true
        return this
    }

    fun setAsExitOption(): MenuItem
    {
        isExitOption = true
        return this
    }

    internal fun run(): Boolean
    {
        if (menu != null) menu!!.display()
        else if (action != null) action!!()
        return !isExitOption
    }

    override fun equals(other: Any?): Boolean
    {
        if (other === this) return true
        if (other !is MenuItem) return false
        val menuItem = other as MenuItem?
        return menuItem!!.id == id
    }

    override fun hashCode(): Int
    {
        return id.hashCode()
    }
}

