package com.showmeyourcode.cqrseventsourcing.demo.query.updatesbyproduct

import com.showmeyourcode.cqrseventsourcing.demo.domain.Query
import java.util.*

class GetUpdatesByProductQuery : Query<GetUpdatesByProductQueryResult>

class GetUpdatesByProductQueryResult(
    val updatesByProduct : Map<UUID, Int>
)