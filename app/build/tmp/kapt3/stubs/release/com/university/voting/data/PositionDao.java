package com.university.voting.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u0010\u00c0\u0006\u0003"}, d2 = {"Lcom/university/voting/data/PositionDao;", "", "insert", "", "position", "Lcom/university/voting/data/Position;", "(Lcom/university/voting/data/Position;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPositionsForElection", "", "electionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPositions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPositionById", "positionId", "app_release"})
@androidx.room.Dao()
public abstract interface PositionDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.university.voting.data.Position position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT p.* FROM positions p INNER JOIN elections e ON p.positionId = e.positionId WHERE e.electionId = :electionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPositionsForElection(@org.jetbrains.annotations.NotNull()
    java.lang.String electionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.Position>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM positions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllPositions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.university.voting.data.Position>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM positions WHERE positionId = :positionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPositionById(@org.jetbrains.annotations.NotNull()
    java.lang.String positionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.university.voting.data.Position> $completion);
}