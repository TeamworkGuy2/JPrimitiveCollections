JPrimitiveCollections
==============
version: 0.2.1

Primitive data collections (char, int, float, long, double), including:
* array lists - primitive list API mirroring java.util.List
* bags (unordered lists) - a list of primitives which does not define a result order when an item is removed
* sorted lists - a sorted list of primitives, with no way to set or insert a value at a specific index to ensure the list remains sorted
* sorted maps - associate a primitive key with a generic value
* array views - wrap a primitive array, offset, and size in a list like API
* iterators - with primitive and boxed wrapper versions
* read only interfaces

The primary purpose of these collections are to provide performance improvements over equivalent java collections such as HashMap and ArrayList.

Take a look at the 'test' package for some examples of how the API works.
