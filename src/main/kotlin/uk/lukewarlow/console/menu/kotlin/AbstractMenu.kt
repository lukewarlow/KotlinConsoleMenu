package uk.lukewarlow.console.menu.kotlin

import java.util.*

abstract class AbstractMenu protected constructor(private var title: String)
{
    private val menuItems: MutableList<MenuItem>

    init
    {
        menuItems = ArrayList()
        init()
    }

    protected abstract fun init()

    protected open fun updateMenuItems() {}

    fun display()
    {
        val scanner = Scanner(System.`in`)
        var repeat = true
        while (repeat)
        {
            updateMenuItems()
            println()
            println(title)
            for (i in menuItems.indices)
            {
                if (menuItems[i].isVisible) println("$i. ${menuItems[i].description}")
            }
            print("Select Option: ")
            val input = scanner.nextLine()
            try
            {
                val itemIndex = input.toInt()
                val menuItem = menuItems[itemIndex]
                if (menuItem.isVisible) repeat = menuItem.run()
                else throw InputMismatchException()
            }
            catch (e: NumberFormatException)
            {
                println("Invalid option, you need to enter a number.")
                repeat = true
            }
            catch (e: IndexOutOfBoundsException)
            {
                println("Invalid option. Option $input doesn't exist.")
                repeat = true
            }
            catch (e: InputMismatchException)
            {
                println("Invalid option. Option $input is hidden.")
                repeat = true
            }
        }
    }

    fun addMenuItem(menuItem: MenuItem)
    {
        if (!menuItems.contains(menuItem)) menuItems.add(menuItem)
        else throw IllegalArgumentException("Menu item with id " + menuItem.id + " already exists!")
    }

    fun addHiddenMenuItem(menuItem: MenuItem)
    {
        addMenuItem(menuItem.hide())
    }

    fun showMenuItem(itemId: Long)
    {
        try
        {
            val menuItem = MenuItem(itemId)
            menuItems[menuItems.indexOf(menuItem)].show()
        }
        catch (e: IndexOutOfBoundsException)
        {
            throw IllegalArgumentException("Error showing menu item. Menu item with ID $itemId hasn't been added to this menu.")
        }
    }

    fun hideMenuItem(itemId: Long)
    {
        try
        {
            val menuItem = MenuItem(itemId)
            menuItems[menuItems.indexOf(menuItem)].show()
        }
        catch (e: IndexOutOfBoundsException)
        {
            throw IllegalArgumentException("Error showing menu item. Menu item with ID $itemId hasn't been added to this menu.")
        }
    }
}