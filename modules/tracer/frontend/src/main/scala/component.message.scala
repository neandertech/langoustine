package langoustine.tracer

import com.raquo.laminar.api.L.*

val fromClient = textAlign.left
val fromServer = textAlign.right

def renderMessage(
    showing: Var[Option[Message]]
)(id: String, initial: Message, stream: Signal[Message]) =
  import Message.*

  initial match
    case rq: Request =>
      renderRequest(
        rq,
        stream.changes.collect { case rq: Request => rq },
        showing
      )
    case rp: Response =>
      renderResponse(
        rp,
        stream.changes.collect { case rp: Response => rp },
        showing
      )
    case cm: Notification =>
      renderNotification(
        cm,
        stream.changes.collect { case nt: Notification => nt },
        showing
      )
  end match
end renderMessage

import Message.*

inline def select(req: Message, showing: Var[Option[Message]]) =
  Seq(
    background <-- showing.signal.map {
      case Some(`req`) =>
        "repeating-linear-gradient(45deg, #ededed, #ededed 10px, white 10px, white 20px)"
      case _ => ""
    }
  )

def renderNotification(
    nt: Notification,
    changes: EventStream[Notification],
    showing: Var[Option[Message]]
) =
  div(
    select(nt, showing),
    Styles.timeline.row,
    if (nt.direction == Direction.ToClient)
    then fromServer
    else fromClient,
    button(
      Styles.timeline.notificationButton,
      nt.method,
      onClick.preventDefault.mapTo(nt) --> showing.someWriter
    )
  )

def renderRequest(
    rq: Request,
    changes: EventStream[Request],
    showing: Var[Option[Message]]
) =
  div(
    Styles.timeline.row,
    fromClient,
    select(rq, showing),
    button(
      Styles.timeline.requestButton,
      b(rq.method),
      ": ",
      cid(rq.id),
      onClick.preventDefault.mapTo(rq) --> showing.someWriter
    ),
    child.maybe <-- changes.startWith(rq).map { rq =>
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
    }
  )

def renderResponse(
    rp: Response,
    changes: EventStream[Response],
    showing: Var[Option[Message]]
) =
  div(
    select(rp, showing),
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
      child.maybe <-- changes.startWith(rp).map { rp =>
        Option.when(rp.method.isDefined) {
          p(
            margin := "0px",
            a(
              Styles.timeline.seeLink,
              href := "#",
              small("see request ", b(cid(rp.id))),
              onClick.preventDefault.mapTo(
                rp.method
                  .map(method => Request(method, rp.id, responded = true))
              ) --> showing.writer
            )
          )
        }
      }
    )
  )
