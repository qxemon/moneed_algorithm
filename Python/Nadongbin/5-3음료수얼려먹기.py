# DFS/BFS

N, M = map(int, input().split(" "))

# 2차원 리스트의 맵 정보 입력 받기
graph = []
for i in range(N):
    graph.append(list(map(int, input().split())))

def dfs(i, j):
    