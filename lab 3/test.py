
def power_func(x,n):
        if n == 0:
            return 1
        elif n < 0:
            return 1 / power_func(-x)
        elif n % 2 == 0:
            return power_func(x * x, n // 2)
        else:
            return x * power_func(x * x, (n - 1) // 2)


print(power_func(13906381785202400310554386903223887989447413475335,16530414601591569469683065465165413915800085899663))