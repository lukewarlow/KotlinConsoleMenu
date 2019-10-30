package uk.lukewarlow.console.menu.kotlin

class TestSubMenu : AbstractMenu("Welcome to the test sub menu.")
{
    override fun init()
    {
        addMenuItem(MenuItem(0, "Exit current menu").setAsExitOption())
        addMenuItem(MenuItem(1, "Test sub menu item") {println("Test sub menu item selected")})
    }
}