#JAVA设计模式
设计模式用于解决反复出现的问题，是解决特定问题的指导方针。设计模式不是在应用中引用的类、package 或者库，而是在某些特定场景下解决特定问题的指导方针。

设计模式用于解决反复出现的问题，是解决某些特定问题的指导方针。

维基百科中这样描述设计模式：

    在软件工程中，设计模式是针对软件设计中普遍存在（反复出现）的各种问题，所提出的可复用型解决方案。
    设计模式并不直接完成代码的编写，而是描述在不同情况下如何解决问题。
**注意：**

1.设计模式并非解决所有问题的银弹。

2.不要强制使用设计模式，否则结果可能适得其反。谨记：设计模式是用来解决问题的，而不是来寻找问题的，不要过度思考。
如果在对的地方对的时机使用设计模式，它会是你的救世主。反之，将会一团糟。

##创建型模式：

抽象工厂模式：抽象工厂模式提供了一个协议来生成一系列的相关或者独立的对象，而不用指定具体对象的类型，如 java.util.Calendar#getInstance()。

建造模式(Builder)：定义了一个新的类来构建另一个类的实例，以简化复杂对象的创建，如：java.lang.StringBuilder#append()。

工厂方法：就是 一个返* 回具体对象的方法，而不是多个，如 java.lang.Object#toString()、java.lang.Class#newInstance()。

原型模式：使得类的实例能够生成自身的拷贝、如：java.lang.Object#clone()。

单例模式：全局只有一个实例，如 java.lang.Runtime#getRuntime()。

##结构型模式

适配器：用来把一个接口转化成另一个接口，如 java.util.Arrays#asList()。

桥接模式：这个模式将抽象和抽象操作的实现进行了解耦，这样使得抽象和实现可以独立地变化，如JDBC；

组合模式：使得客户端看来单个对象和对象的组合是同等的。换句话说，某个类型的方法同时也接受自身类型作为参数，如 Map.putAll，List.addAll、Set.addAll。

装饰者模式：动态的给一个对象附加额外的功能，这也是子类的一种替代方式，如 java.util.Collections#checkedList|Map|Set|SortedSet|SortedMap。

享元模式：使用缓存来加速大量小对象的访问时间，如 valueOf(int)。

代理模式：代理模式是用一个简单的对象来代替一个复杂的或者创建耗时的对象，如 java.lang.reflect.Proxy

##行为模式

责任链模式：通过把请求从一个对象传递到链条中下一个对象的方式，直到请求被处理完毕，以实现对象间的解耦。如 javax.servlet.Filter#doFilter()。

命令模式：将操作封装到对象内，以便存储，传递和返回，如：java.lang.Runnable。

解释器模式：定义了一个语言的语法，然后解析相应语法的语句，如，java.text.Format，java.text.Normalizer。

迭代器模式：提供一个一致的方法来顺序访问集合中的对象，如 java.util.Iterator。

中介者模式：通过使用一个中间对象来进行消息分发以及减少类之间的直接依赖，java.lang.reflect.Method#invoke()。

空对象模式：如 java.util.Collections#emptyList()。

观察者模式：它使得一个对象可以灵活的将消息发送给感兴趣的对象，如 java.util.EventListener。

模板方法模式：让子类可以重写方法的一部分，而不是整个重写，如 java.util.Collections#sort()。