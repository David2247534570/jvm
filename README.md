# JVM 知识学习

## 项目介绍

这是一个专注于 Java 虚拟机（JVM）深度学习和实践的项目，通过丰富的示例代码和实际演示帮助理解 JVM
内部工作原理。项目采用理论与实践相结合的方式，涵盖了类加载、字节码、内存模型、垃圾回收、并发编程等 JVM 核心知识点。

## 🛠 技术栈

- **Java 8** - 基础开发语言
- **Maven** - 项目构建工具
- **jcstress (v0.14)** - Java 并发压力测试框架
- **Lombok** - 减少样板代码
- **ASM** - 字节码操作框架

## 📁 项目结构

```
src/main/java/com/ltx/
├── bytecode/          # 📝 字节码分析
├── classload/         # 🔄 类加载机制
├── constant/          # 🔢 常量定义
├── directmemory/      # 💾 直接内存操作
├── entity/            # 👤 实体类
├── exercise/          # 💡 高级练习示例
├── gc/                # 🗑️ 垃圾回收
├── heap/              # 🏗️ 堆内存管理
├── jmm/               # 🧠 Java 内存模型
├── methodarea/        # 📚 方法区内存
├── reference/         # 🔗 引用类型
├── stack/             # 📚 栈内存管理
├── stringpool/        # 🧵 字符串常量池
├── util/              # 🛠️ 工具类
└── Main.java          # 🚀 主入口
```

## 🎯 核心模块详解

### 1. 字节码 (bytecode) - JVM 指令集解码器

深入理解 Java 代码如何编译为字节码指令：

- **AutoIncrement.java** - 自增操作的底层指令分析，展示 `i++` 和 `++i` 的字节码差异
- **Clinit.java** - 类构造器 `<clinit>` 方法演示，静态变量初始化顺序
- **Finally.java** - finally 块原理，返回值覆盖和异常处理的字节码实现
- **Init.java** - 实例构造器 `<init>` 方法分析
- **MethodInvocation.java** - 方法调用的字节码指令（invokevirtual, invokestatic 等）

> 📚 **学习要点**：理解每条字节码指令的含义，掌握编译器优化策略

### 2. 类加载机制 (classload) - JVM 启动的第一道门

完整的类加载生命周期演示：

- **Initialization.java** - 类初始化时机和触发条件，包含 8 种不会触发初始化的情况
- **Resolution.java** - 类解析阶段，符号引用转换为直接引用的过程
- **CustomClassLoader.java** - 自定义类加载器实现，演示双亲委派机制

> 🎯 **关键概念**：加载、链接（验证、准备、解析）、初始化 三大阶段

### 3. Java 内存模型 (jmm) - 并发编程的理论基础

深入 JMM 三大特性：

- **Atomicity.java** - 原子性问题演示，展示 `i++` 操作的非原子性和 synchronized 解决方案
- **Visibility.java** - 可见性问题，volatile 关键字的工作原理
- **Ordering.java** - 有序性问题，使用 jcstress 框架进行并发压力测试
- **CAS.java** - Compare-And-Swap 操作实现，基于 Unsafe 的无锁编程

> 🔬 **测试工具**：jcstress 框架用于测试多线程环境下的内存模型行为

### 4. 垃圾回收 (gc) - 内存的自动管家

GC 算法和机制演示：

- **ReachabilityAnalysis.java** - 可达性分析算法解决循环引用问题
- **StringDeduplication.java** - 字符串去重机制
- **Analysis.java** - GC 行为分析和监控
- **Node.java** - GC 节点引用模型

> ♻️ **核心算法**：可达性分析、标记-清除、复制、标记-整理

### 5. 内存区域管理 - JVM 内存布局实践

#### 堆内存 (heap)

- **HeapMemoryOverflow.java** - 堆内存溢出演示，字符串对象快速增长

#### 栈内存 (stack)

- **StackMemoryOverflow.java** - 栈帧过多导致的 StackOverflowError，递归深度测试
- **ByteCode.java** - 栈帧结构的字节码分析

#### 方法区 (methodarea)

- **MethodAreaMemoryOverflow.java** - 元空间溢出，使用 ASM 动态生成类字节码

#### 直接内存 (directmemory)

- **DirectMemoryOverflow.java** - 堆外内存溢出，ByteBuffer.allocateDirect() 使用
- **DirectMemoryRecycling.java** - 直接内存回收机制

### 6. 引用类型 (reference) - 内存管理的精细控制

- **SoftReferenceApplication.java** - 软引用，内存敏感缓存实现
- **WeakReferenceApplication.java** - 弱引用，WeakHashMap 原理演示

### 7. 字符串常量池 (stringpool) - 不可变对象的奥秘

- **StringPool.java** - 字符串常量池机制，编译期优化分析
- **StringLazyLoad.java** - 字符串懒加载机制
- **StringPoolGC.java** - 字符串常量池的垃圾回收
- **StringPoolPosition.java** - 字符串常量池在内存中的位置变化

> 💡 **核心要点**：`"a" + "b"` 在编译期优化，`s1 + s2` 在运行期处理

### 8. 高级练习 (exercise) - 深入 JVM 内部

#### 面向对象机制

- **EqualsAndHashCode.java** - equals 和 hashCode 契约，对 HashSet/HashMap 的影响
- **Polymorphic.java** - 多态性实现原理
- **Genericity.java** - 泛型的类型擦除和桥接方法
- **FieldHasNoPolymorphic.java** - 字段没有多态性的验证

#### 方法调用机制

- **DynamicDispatch.java** - 动态分派，运行时多态的实现
- **StaticDispatch.java** - 静态分派，方法重载的解析过程
- **Overload.java** - 方法重载的各种情况

#### 编译器优化

- **Inlining.java** - 方法内联优化
- **TieredCompilation.java** - 分层编译机制
- **ReflectionOptimization.java** - 反射调用优化

### 9. 工具类 (util) - JVM 开发的瑞士军刀

- **UnsafeUtil.java** - Unsafe 工具类，内存操作和 CAS 的底层实现
- **ReflectionUtil.java** - 反射工具集
- **StackTraceUtil.java** - 堆栈跟踪分析
- **ReferenceUtil.java** - 引用类型操作工具

## 🚀 快速开始

### 环境要求

- JDK 8+
- Maven 3.x

### 基础运行

```bash
# 编译项目
mvn clean compile

# 运行字节码分析示例
mvn exec:java -Dexec.mainClass="com.ltx.bytecode.AutoIncrement"

# 运行类加载机制示例
mvn exec:java -Dexec.mainClass="com.ltx.classload.Initialization"

# 运行内存模型示例
mvn exec:java -Dexec.mainClass="com.ltx.jmm.Atomicity"

# 运行垃圾回收示例
mvn exec:java -Dexec.mainClass="com.ltx.gc.ReachabilityAnalysis"
```

### 并发测试

```bash
# 运行 jcstress 并发压力测试
mvn exec:java -Dexec.mainClass="com.ltx.jmm.Ordering"
```

## 🔧 JVM 调试参数

### 内存配置参数

```bash
# 堆内存配置（用于溢出演示）
-Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError

# 栈内存配置
-Xss128k

# 方法区/元空间配置
-XX:MaxMetaspaceSize=10m

# 直接内存配置
-XX:MaxDirectMemorySize=10m
```

### 调试和监控参数

```bash
# 类加载跟踪
-XX:+TraceClassLoading

# GC 日志
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps

# 字节码查看
-XX:+PrintAssembly
```

## 📖 学习路径建议

### 🌱 入门阶段

1. **内存区域基础** - heap/、stack/、methodarea/ 示例
2. **类加载机制** - classload/ 模块的完整流程
3. **字节码入门** - bytecode/ 基础指令分析

### 🌿 进阶阶段

1. **垃圾回收** - gc/ 模块的算法和收集器
2. **字符串常量池** - stringpool/ 的内存优化
3. **引用类型** - reference/ 的内存管理策略

### 🌳 高级阶段

1. **Java 内存模型** - jmm/ 的并发理论基础
2. **方法调用优化** - exercise/ 中的分派和优化
3. **Unsafe 操作** - util/ 中的底层内存操作

### 🌄 专家阶段

1. **并发压力测试** - jcstress 框架应用
2. **字节码操作** - ASM 和动态类生成
3. **性能调优** - 综合调优案例分析

## 🧪 测试和验证

### 内存溢出演示

项目包含多种内存溢出场景的安全演示：

```bash
# 堆内存溢出（需要调整堆大小）
mvn exec:java -Dexec.mainClass="com.ltx.heap.HeapMemoryOverflow" -Dexec.args="-Xms10m -Xmx10m"

# 栈内存溢出
mvn exec:java -Dexec.mainClass="com.ltx.stack.StackMemoryOverflow" -Dexec.args="-Xss128k"

# 方法区溢出
mvn exec:java -Dexec.mainClass="com.ltx.methodarea.MethodAreaMemoryOverflow" -Dexec.args="-XX:MaxMetaspaceSize=10m"
```

### 并发测试验证

```bash
# 原子性验证
mvn exec:java -Dexec.mainClass="com.ltx.jmm.Atomicity"

# 有序性测试
mvn exec:java -Dexec.mainClass="com.ltx.jmm.Ordering"
```

## 💡 核心学习要点

### 🎯 必须掌握的概念

- **类加载生命周期**：加载 → 链接 → 初始化
- **JMM 三大特性**：原子性、可见性、有序性
- **GC 算法**：标记-清除、复制、标记-整理
- **内存区域划分**：堆、栈、方法区、程序计数器

### 🔍 深度理解点

- **字节码指令集**：每条指令的具体含义和执行效果
- **方法调用机制**：静态分派 vs 动态分派
- **字符串常量池**：编译期优化 vs 运行期处理
- **引用类型**：强引用、软引用、弱引用、虚引用的区别

### ⚡ 性能优化技巧

- **内存分配策略**：对象在堆中的分配规律
- **GC 调优参数**：根据应用场景选择合适的收集器
- **并发编程**：CAS、volatile、synchronized 的正确使用

## 🚨 注意事项

### 安全运行提醒

- **内存溢出演示**：使用合理的内存限制，避免系统资源耗尽
- **死锁/挂起**：某些线程示例可能需要手动终止进程
- **大量输出**：某些示例会产生大量控制台输出
- **并发不确定性**：多线程示例结果可能因运行环境而异

### 开发环境配置

- **Java 版本**：确保使用 Java 8 或更高版本
- **IDE 配置**：建议开启字节码查看插件
- **内存监控**：使用 JConsole 或 VisualVM 监控内存使用

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request：

1. **Bug 报告**：详细的错误信息和复现步骤
2. **功能建议**：新的 JVM 概念示例
3. **代码优化**：现有示例的改进和优化
4. **文档完善**：README、注释和学习指南

## 📄 许可证

MIT License - 自由使用、修改和分发

---

⭐ 如果这个项目对学习 JVM 有帮助，请给个 Star 支持一下！