IntelliGuard 2
============

>Updated and maintained by iGufGuf, originally created by [Ronniekk](https://github.com/ronniekk/intelliguard)
>
>Created and tested for intellij 2018.2

### Features

Obfuscation made easy.

IntelliGuard integrates yGuard - a free Java bytecode obfuscator - with IntelliJ IDEA. Key features are:

* No more hazzles with configuration files. Just hit ALT+ENTER on any symbol in the editor for keep options.
* Refactor support. Kept symbols remain unobfuscated after rename or move.
* Inspections for common obfuscation mistakes.
* Optional (toggleable) gutter icons for obfuscated symbols.
* Export configuration for [yGuard](http://www.yworks.com/en/products_yguard_about.html) and [ProGuard](http://proguard.sourceforge.net/) format.


### General usage

Add Obfuscation facet to your Java module to get going, 
"Project Structure (CTRL+SHIFT+ALT+S)" or "Module Settings" in project explorer. 
Obfuscation actions will only be visible in an Obfuscation facet.

You can build the jar file from your module in the "build" menu.


