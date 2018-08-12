package uk.co.lukewarlow.console.menu.kotlin

class TestMenu : AbstractMenu("Welcome to the test menu.")
{
    private var showHiddenMenu: Boolean = false

    override fun init()
    {
        addMenuItem(MenuItem(0, "Exit menu").setAsExitOption())
        addMenuItem(MenuItem(1, "Test submenu", TestSubMenu()))
        addMenuItem(MenuItem(2, "Show hidden menu item") {
            println("Showing hidden menu item")
            showHiddenMenu = true
        })
        addHiddenMenuItem(MenuItem(3, "Hidden menu item") {println("I was a hidden menu item")})
    }

    override fun updateMenuItems()
    {
        if (showHiddenMenu) showMenuItem(3)
    }
}