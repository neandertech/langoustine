class S0()
class S1()
class S2()

object types:
  opaque type Filter = S0 | S1

import types.*

def x(value: Filter | S2) =
  value match
    case _: Filter => 1
    case _: S2     => 2
