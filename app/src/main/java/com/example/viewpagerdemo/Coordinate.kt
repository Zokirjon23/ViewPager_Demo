package com.example.viewpagerdemo

data class Coordinate(
    val list: List<Point>,
    var currentPos: Int = 0
){

    fun currentX() : Float{
        return list[currentPos].x
    }
    fun currentY() : Float{
        return list[currentPos].y
    }
}



/**
 * tables foods pos
 */

val table2Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.3755f, 0.336f),
            Point(0.68f, 0.256f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.633f, 0.4f),
            Point(0.457f, 0.232f),
        )
    ),
    Coordinate(
        listOf(Point(0.578f, 0.176f))
    ),
    Coordinate(
        listOf(
            Point(0.527f, 0.31f),
        )
    ),
    Coordinate(
        listOf(
            Point(0.239f, 0.281f),
            Point(0.311f, 0.262f),
            Point(0.279f, 0.295f)
        )
    ),
)

val table4Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.248f, 0.26f),
            Point(0.5f, 0.195f),
            Point(0.421f, 0.42f),
            Point(0.698f, 0.344f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.7f, 0.237f),
            Point(0.176f, 0.397f),
            Point(0.367f, 0.236f),
            Point(0.56f, 0.384f)
        )
    ),
    Coordinate(
        listOf(Point(0.267f, 0.47f))
    ),
    Coordinate(
        listOf(
            Point(0.3f, 0.35f),
            Point(0.57f, 0.27f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.08f, 0.32f),
            Point(0.13f, 0.31f),
            Point(0.109f, 0.345f)
        )
    ),
)

val table6Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.327f,0.253f),
            Point(0.546f,0.189f),
            Point(0.496f,0.431f),
            Point(0.732f,0.35f),
            Point(0.242f,0.391f),
            Point(0.75f,0.22f),
        )
    ),
    Coordinate(
        listOf(
            Point(0.621f, 0.245f),
            Point(0.675f, 0.29f),
            Point(0.347f, 0.325f),
            Point(0.395f, 0.388f)
        )
    ),
    Coordinate(
        listOf(Point(0.328f, 0.495f))
    ),
    Coordinate(
        listOf(
            Point(0.44f,0.334f),
            Point(0.58f,0.292f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.14f,0.33f),
            Point(0.21f,0.32f),
            Point(0.18f,0.355f)
        )
    ),
)

val table8Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.298f,0.303f),
            Point(0.475f,0.244f),
            Point(0.635f,0.184f),
            Point(0.798f,0.211f),
            Point(0.785f,0.321f),
            Point(0.613f,0.389f),
            Point(0.421f,0.466f),
            Point(0.237f,0.424f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.341f, 0.365f),
            Point(0.378f, 0.41f),
            Point(0.521f, 0.297f),
            Point(0.565f, 0.34f),
            Point(0.675f, 0.236f),
            Point(0.723f, 0.277f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.289f, 0.522f),
            Point(0.723f, 0.15f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.442f,0.352f),
            Point(0.621f,0.289f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.14f,0.36f),
            Point(0.195f,0.36f),
            Point(0.167f,0.385f)
        )
    )
)

val table10Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.248f,0.321f),
            Point(0.41f,0.261f),
            Point(0.543f,0.21f),
            Point(0.666f,0.163f),
            Point(0.822f,0.202f),
            Point(0.832f,0.321f),
            Point(0.699f,0.376f),
            Point(0.55f,0.439f),
            Point(0.372f,0.519f),
            Point(0.712f,0.301f),
        )
    ),
    Coordinate(
        listOf(
            Point(0.336f, 0.361f),
            Point(0.388f, 0.439f),
            Point(0.505f, 0.289f),
            Point(0.559f, 0.36f),
            Point(0.643f, 0.236f),
            Point(0.723f, 0.277f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.289f, 0.522f),
            Point(0.723f, 0.15f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.442f,0.352f),
            Point(0.621f,0.289f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.14f,0.36f),
            Point(0.195f,0.36f),
            Point(0.167f,0.385f)
        )
    )
)

val table12Coordinate = listOf(
    Coordinate(
        listOf(
            Point(0.298f,0.303f),
            Point(0.475f,0.244f),
            Point(0.635f,0.184f),
            Point(0.798f,0.211f),
            Point(0.785f,0.321f),
            Point(0.613f,0.389f),
            Point(0.421f,0.466f),
            Point(0.237f,0.424f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.341f, 0.365f),
            Point(0.378f, 0.41f),
            Point(0.521f, 0.297f),
            Point(0.565f, 0.34f),
            Point(0.675f, 0.236f),
            Point(0.723f, 0.277f)
        )
    ),
    Coordinate(
        listOf(
            Point(0.289f, 0.522f),
            Point(0.723f, 0.15f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.442f,0.352f),
            Point(0.621f,0.289f)
        )
    ),

    Coordinate(
        listOf(
            Point(0.14f,0.36f),
            Point(0.195f,0.36f),
            Point(0.167f,0.385f)
        )
    )
)
