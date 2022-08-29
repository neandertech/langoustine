package langoustine.tracer

import com.raquo.laminar.api.L.*

val fromClient = textAlign.left
val fromServer = textAlign.right

def renderMessage(
    showing: Var[Option[Message]]
)(id: String, initial: Message, stream: Signal[Message]) =
  import Message.*

  inline def select(req: Message) =
    Seq(
      background <-- showing.signal.map {
        case Some(`req`) =>
          "repeating-linear-gradient(45deg, #ededed, #ededed 10px, white 10px, white 20px)"
        case _ => ""
      }
    )

  println(s"Being called for $id")
  div(
    child <-- stream.map {
      case rq: Request =>
        div(
          Styles.timeline.row,
          fromClient,
          select(rq),
          button(
            Styles.timeline.requestButton,
            b(rq.method),
            ": ",
            cid(rq.id),
            onClick.preventDefault.mapTo(rq) --> showing.someWriter
          ),
          Option.when(rq.responded) {
            p(
              margin := "0px",
              a(
                Styles.timeline.seeLink,
                href := "#",
                small("see response"),
                onClick.preventDefault.mapTo(
                  Response(rq.id, method = Some(rq.method))
                ) --> showing.someWriter
              )
            )
          }
        )
      case rp: Response =>
        div(
          select(rp),
          Styles.timeline.row,
          fromServer,
          div(
            button(
              Styles.timeline.requestButton,
              rp.method match
                case Some(m) =>
                  span(
                    b(m),
                    " response"
                  )
                case None =>
                  b(s"Response for ${cid(rp.id)}")
              ,
              onClick.preventDefault.mapTo(rp) --> showing.someWriter
            ),
            Option.when(rp.method.isDefined) {
              p(
                margin := "0px",
                a(
                  Styles.timeline.seeLink,
                  href := "#",
                  small("see request ", b(cid(rp.id))),
                  onClick.preventDefault.mapTo(
                    rp.method.map(method =>
                      Request(method, rp.id, responded = true)
                    )
                  ) --> showing.writer
                )
              )
            }
          )
        )
      case cm: Notification =>
        div(
          select(cm),
          Styles.timeline.row,
          if (cm.direction == Direction.ToClient)
          then fromServer
          else fromClient,
          button(
            Styles.timeline.notificationButton,
            cm.method,
            onClick.preventDefault.mapTo(cm) --> showing.someWriter
          )
        )
    }
  )
end renderMessage
