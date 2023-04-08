from collections import deque

class Graph:

    class GraphNode:
        def __init__(self, j1, j2, j3, signed = False, prev = None):
            self.state = [j1, j2, j3]
            self.signed = signed
            self.prev = prev
            self.connect = []

        def __repr__(self):
            return "[" + ", ".join(repr(item) for item in self.state) + "]"

    def __init__(self, m1, m2, m3, target):
        self.max = [m1, m2, m3]
        self.target = target
        self.nodemap = {}
        for i in range(m1 + 1):
            for j in range(m2 + 1):
                for k in range(m3 + 1):
                    node = Graph.GraphNode(i, j, k, False)
                    self.nodemap[node] = None

    def isValid(self, node: GraphNode):
        for i in range(3):
            if node.state[i] > self.max[i] or node.state[i] < 0:
                return False
        return True


    # Something went wrong
    def stateChange(self, node: GraphNode):
        smap = []
        state = node.state

        # Generate empty states
        for i in range(3):
            cnode = self.GraphNode(state[0], state[1], state[2], False)
            or1 = cnode
            or1.state[i] = 0
            for x in self.nodemap:
                n = 0
                for i in range(3):
                    if x.state[i] is cnode.state[i]:
                        n += 1
                if n == 3:
                    x.prev = node
                    smap.append(x)
            #smap.append(or1)
            #cnode.prev = node
        # Generate full states
        for i in range(3):
            cnode = self.GraphNode(state[0], state[1], state[2], False)
            or2 = cnode
            or2.state[i] = self.max[i]
            for x in self.nodemap:
                n = 0
                for i in range(3):
                    if x.state[i] is cnode.state[i]:
                        n += 1
                if n == 3:
                    x.prev = node
                    smap.append(x)
            #smap.append(or2)
            #cnode.prev = node
        # Generate right rotation pouring, could be optimized
        for i in range(3):
            cnode = self.GraphNode(state[0], state[1], state[2], False)
            st = cnode.state
            # 1 -> 2
            if not st[i] == 0:
                if i == 2:
                    if st[0] is self.max[0] and st[i] is self.max[i]:
                        continue
                    diff = abs(st[i] - st[0])
                    if st[0] + diff > self.max[0]:
                        st[0] = self.max[0]
                        st[i] -= self.max[0]
                    else:
                        st[i] -= diff
                        st[0] += diff
                else:
                    if st[i+1] is self.max[i+1] and st[i] is self.max[i]:
                        continue
                    diff = abs(st[i] - st[i+1])
                    if st[i+1] + diff > self.max[i+1]:
                        st[i+1] = self.max[i+1]
                        st[i] -= self.max[i+1]
                    else:
                        st[i] -= diff
                        st[i+1] += diff
            for x in self.nodemap:
                n = 0
                for i in range(3):
                    if x.state[i] is cnode.state[i]:
                        n += 1
                if n == 3:
                    x.prev = node
                    smap.append(x)
            #smap.append(cnode)
            #cnode.prev = node
        # Generate left rotation pouring, could be optimized
        for i in range(3):
            cnode = self.GraphNode(state[0], state[1], state[2], False)
            st = cnode.state
            # 2 -> 1
            if not st[i] == 0:
                if i == 0:
                    if st[2] is self.max[2] and st[i] is self.max[i]:
                        continue
                    diff = abs(st[i] - st[2])
                    if st[2] + diff > self.max[2] and st[2]:
                        st[2] = self.max[2]
                        st[i] -= self.max[2]
                    else:
                        st[i] -= diff
                        st[2] += diff
                else:
                    if st[i-1] is self.max[i-1] and st[i] is self.max[i]:
                        continue
                    diff = abs(st[i] - st[i-1])
                    if st[i-1] + diff > self.max[i-1] and st[i-1]:
                        st[i-1] = self.max[i-1]
                        st[i] -= self.max[i-1]
                    else:
                        st[i] -= diff
                        st[i-1] += diff
            for x in self.nodemap:
                n = 0
                for i in range(3):
                    if x.state[i] is cnode.state[i]:
                        n += 1
                if n == 3:
                    x.prev = node
                    smap.append(x)
            #smap.append(cnode)
            #cnode.prev = node

        # Assign node.prev to be the node of origin for backlooping once solution is found
        return smap

    # Generate map of valid connecting nodes
    def next(self, node: GraphNode):
        n = []
        for y in self.stateChange(node):
            if self.isValid(y) and y is not node:
                n.append(y)
        return n

    # Check if the target is in the current node
    def solved(self, node: GraphNode):
        if self.target in node.state:
            return True
        return False

    def BFS(self, start: GraphNode):
        if t > max(self.max):
            return "No Solution, Target Greater Than Maximum Jug Size"
        path = []
        q = deque()
        q.append(start)
        start.signed = True

        while q:
            nx = q.popleft()
            nodepath = self.next(nx)

            if self.solved(nx):
                path.append(nx)

                # Solution found, go back until root node
                while nx.prev:
                    path.append(nx.prev)
                    nx = nx.prev
                path.reverse() # Appending provides backward, so reverse and return the correct path
                return path

            for a in nodepath:
                if not a.signed:
                    q.append(a)
                    a.signed = True
        return None





j1 = int(input("Jug 1: "))
j2 = int(input("Jug 2: "))
j3 = int(input("Jug 3: "))
t = int(input("Target: "))
gr = Graph(j1, j2, j3, t)
#gr = Graph(10, 5, 6, 8)
start = gr.GraphNode(0, 0, 0, False)
print(gr.BFS(start))
#print(gr.nodemap)
#print(gr.next(start))
