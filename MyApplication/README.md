### hencoder自定义View1-1（学习与作业）

    尽量一周2更，早点跟上大佬节奏，学习文档的正确写法和gif制作，最关键的是自己的理解
    
#### 自定义view的关键点：

* 绘制 (常用ondraw())
* 裁切 (clip)
* 几何变换
* 绘制顺序

上面的四点，分别涵盖了绘制的主体，显示部分，发生的变换，以及绘制先后所产生的不同的显示结果，以上将通过8个章节的
学习，一步步的深入每一个关键点的具体作用，和正确的使用方法，具体每个方法的作用详情可以转到[hencoder](http://hencoder.com)

第一章陌生知识点：

Path.setFillStyle()

参数 Path.FillType 一共有四种

    public static enum FillType {
        INVERSE_EVEN_ODD,
        INVERSE_WINDING,
        EVEN_ODD, // 奇偶
        WINDING;  // 默认值

        private FillType() {
        }
    }


前提 ： paint.setStyle( Fill / FILL_OR_STROKEN)

setFillStyle的作用：在WINDING / EVEN_ODD的情况下，内容区域FILL而非内容区域不填充，而这两种FillStyle拥有不
同的内容区域算法，而这种算法的主要的依据是path的方向



    

    
    
    
    
    