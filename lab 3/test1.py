def power_func(x,n):
        result = 1
        if n < 0:
            n = -n
            x = 1 / x
        while n > 0:
            if n % 2 == 1:
                result *= x
            x *= x
            n //= 2
        return result


print(power_func(13906381785202400310554386903223887989447413475335,16530414601591569469683065465165413915800085899663))