
data class Bat(val location: Location, val width: Double, val height: Double)

fun isWithinBounds(bat: Bat, arenaHeight: Double, margin: Double) =
        bat.location.y - bat.height / 2.0 - margin >= 0.0 && bat.location.y + bat.height / 2.0 + margin <= arenaHeight

fun mapToArenaBounds(bat: Bat, arenaHeight: Double, margin: Double) = Bat(
       Location(
               bat.location.x,
               if (bat.location.y - bat.height / 2.0 - margin < 0.0) bat.height / 2.0 + margin
               else arenaHeight - bat.height / 2.0 - margin
       ),
       bat.width,
       bat.height
   )


fun keepBatInArenaBounds(bat: Bat, arenaHeight: Double, margin: Double) =
        if (isWithinBounds(bat, arenaHeight, margin)) bat
        else mapToArenaBounds(bat, arenaHeight, margin)


fun isWithinBatYBounds(bat: Bat, ball: Ball) =
        ball.center.y >= bat.location.y - bat.height / 2.0 && ball.center.y <= bat.location.y + bat.height / 2.0

fun isTouchingBatSurface(bat: Bat, ball: Ball) = ball.center.x + ball.radius >= bat.location.x - bat.width / 2.0

fun detectCollision(bat: Bat, ball: Ball): Boolean =
        isWithinBatYBounds(bat, ball) && isTouchingBatSurface(bat, ball)
