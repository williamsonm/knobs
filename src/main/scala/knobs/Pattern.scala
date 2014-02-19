package knobs

/**
  * If a pattern written as a literal string does not end with `.*`,
  * it is assumed to be "exact".
  */
sealed trait Pattern {
  def local(pfx: String) = this match {
    case Exact(s) => Exact(pfx ++ s)
    case Prefix(s) => Prefix(pfx ++ s)
  }
}

/** An exact match */
case class Exact(name: Name) extends Pattern

/**
 * A prefix match. Given `Prefix("foo")`, this will match
 * `"foo.bar"`, but not `"foo"` or `"foobar"`.
 */
case class Prefix(name: Name) extends Pattern